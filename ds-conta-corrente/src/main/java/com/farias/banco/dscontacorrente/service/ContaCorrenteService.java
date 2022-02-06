package com.farias.banco.dscontacorrente.service;

import com.farias.banco.dscontacorrente.broker.outbound.ContaCorrenteBrokerOutbound;
import com.farias.banco.dscontacorrente.config.AppConfig;
import com.farias.banco.dscontacorrente.dto.ContaCorrenteDTOResponse;
import com.farias.banco.dscontacorrente.dto.ContaCorrenteProdutoDTO;
import com.farias.banco.dscontacorrente.dto.PessoaContaCorrenteDTO;
import com.farias.banco.dscontacorrente.enums.ContaTipo;
import com.farias.banco.dscontacorrente.enums.PessoaTipoEnum;
import com.farias.banco.dscontacorrente.feignclients.ContaCorrenteProdutosFeignClients;
import com.farias.banco.dscontacorrente.model.ContaCorrente;
import com.farias.banco.dscontacorrente.model.Pessoa;
import com.farias.banco.dscontacorrente.repository.ContaCorrenteRepository;
import com.farias.banco.dscontacorrente.repository.specification.ContaCorrenteSpecification;
import com.farias.banco.dscontacorrente.utils.ContaCorrenteNumeroUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContaCorrenteService {

	private final Logger LOG = LoggerFactory.getLogger(ContaCorrenteService.class);

	private final AppConfig config;
	private final ContaCorrenteRepository repository;
	private final ContaCorrenteProdutosFeignClients contaCorrenteProdutosFeignClients;
	private final ContaCorrenteNumeroUtils contaNumero;
	private final ContaCorrenteBrokerOutbound outbound;

	public ContaCorrente cadastrarContaCorrente(Pessoa pessoa) {
		final var account = repository.save(ContaCorrente.builder()
				.agencia(config.getAgencia().getNumero())
				.numero(contaNumero())
				.pessoa(pessoa.getId())
				.tipo(contaTipo(pessoa.getTipo()))
				.build());

		outbound.vincularProdutosContaCorrentePublish(PessoaContaCorrenteDTO.builder()
				.pessoa(pessoa.getId())
				.contaCorrente(account.getId())
				.score(pessoa.getScore())
				.build());

		outbound.vincularContaCorrentePublish(PessoaContaCorrenteDTO.builder()
				.pessoa(pessoa.getId())
				.contaCorrente(account.getId())
				.score(pessoa.getScore())
				.build());

		return account;
	}

	public List<ContaCorrenteDTOResponse> searchContaCorrentePorPessoa(Long pessoaId) {
		return repository.findByPessoa(pessoaId)
				.parallelStream()
				.map(this::contaCorrenteProdutos)
				.collect(Collectors.toList());
	}

	public Page<ContaCorrenteDTOResponse> contaCorrenteProdutos(final Optional<String> agencia,
			final Optional<String> numero,
			final Optional<String> tipo,
			final Optional<Long> pessoaId,
			final PageRequest pageRequest) {
		return repository.findAll(ContaCorrenteSpecification.builder()
				.agencia(agencia)
				.numero(numero)
				.tipo(tipo)
				.pessoaId(pessoaId)
				.build(), pageRequest)
				.map(this::contaCorrenteProdutos);
	}

	private ContaCorrenteDTOResponse contaCorrenteProdutos(ContaCorrente contaCorrente) {
			List<ContaCorrenteProdutoDTO> produtos = List.of();
			try {
				produtos = contaCorrenteProdutosFeignClients.contaCorrenteProdutos(contaCorrente.getId()).getBody();
			} catch (Exception e) {
				LOG.error("O serviço [ds-conta-corrente-produtos] listar produtos vinculados a conta corrente não está respondendo.", e.getMessage());
			}
			return ContaCorrenteDTOResponse.builder()
					.agencia(contaCorrente.getAgencia())
					.numero(contaCorrente.getNumero())
					.tipo(contaCorrente.getTipo().name())
					.produtos(produtos)
					.build();
	}

	private Integer contaNumero() {
		boolean exists = true;
		Integer conta = null;
		while (exists) {
			conta  = contaNumero.numeroConta();
			List<ContaCorrente> contas = repository.findByNumero(conta);
			if (contas.isEmpty()) exists = false;
		}
		return conta;
	}

	public ContaTipo contaTipo(String pessoaTipo) {
		ContaTipo tipo = null;
		if (pessoaTipo.equals(PessoaTipoEnum.PF.name())) {
			tipo = ContaTipo.C;
		} else if (pessoaTipo.equals(PessoaTipoEnum.PJ.name())) {
			tipo = ContaTipo.E;
		}
		return tipo;
	}
}
