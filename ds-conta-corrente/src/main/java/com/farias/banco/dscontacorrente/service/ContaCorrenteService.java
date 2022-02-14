package com.farias.banco.dscontacorrente.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.farias.banco.dscontacorrente.modules.integration.broker.supplier.ContaCorrenteMessageSupplier;
import com.farias.banco.dscontacorrente.modules.config.app.AppConfig;
import com.farias.banco.dscontacorrente.dto.ContaCorrenteResponseDTO;
import com.farias.banco.dscontacorrente.dto.ContaCorrenteProdutoDTO;
import com.farias.banco.dscontacorrente.enums.ContaTipo;
import com.farias.banco.dscontacorrente.enums.PessoaTipoEnum;
import com.farias.banco.dscontacorrente.modules.integration.feign.ContaCorrenteProdutosFeignClients;
import com.farias.banco.dscontacorrente.modules.model.ContaCorrente;
import com.farias.banco.dscontacorrente.modules.model.Pessoa;
import com.farias.banco.dscontacorrente.modules.repository.ContaCorrenteRepository;
import com.farias.banco.dscontacorrente.modules.repository.specification.ContaCorrenteSpecification;
import com.farias.banco.dscontacorrente.utils.ContaCorrenteNumeroUtils;

import lombok.RequiredArgsConstructor;

import static com.farias.banco.dscontacorrente.constants.MapperConstants.contaCorrenteMapper;
import static com.farias.banco.dscontacorrente.constants.MapperConstants.pessoaMapper;

@Service
@RequiredArgsConstructor
public class ContaCorrenteService {

	private final Logger LOG = LoggerFactory.getLogger(ContaCorrenteService.class);

	private final AppConfig config;
	private final ContaCorrenteRepository repository;
	private final ContaCorrenteProdutosFeignClients contaCorrenteProdutosFeignClients;
	private final ContaCorrenteNumeroUtils contaNumero;
	private final ContaCorrenteMessageSupplier outbound;

	public ContaCorrente cadastrarContaCorrente(Pessoa pessoa) {
		final var account = repository.save(ContaCorrente.builder()
				.agencia(config.getAgencia().getNumero())
				.numero(contaNumero())
				.pessoa(pessoa.getId())
				.tipo(contaTipo(pessoa.getTipo()))
				.build());
		
		final var pessoaContaCorrenteResponse = pessoaMapper.buildPessoaContaCorrenteResponseDTO(pessoa)
				.withContaCorrente(account.getId());

		outbound.vincularProdutosContaCorrentePublish(pessoaContaCorrenteResponse);
		outbound.vincularContaCorrentePublish(pessoaContaCorrenteResponse);
		

		return account;
	}

	public List<ContaCorrenteResponseDTO> searchContaCorrentePorPessoa(Long pessoaId) {
		return repository.findByPessoa(pessoaId)
				.parallelStream()
				.map(this::contaCorrenteProdutos)
				.collect(Collectors.toList());
	}

	public Page<ContaCorrenteResponseDTO> contaCorrenteProdutos(final Optional<String> agencia,
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

	private ContaCorrenteResponseDTO contaCorrenteProdutos(ContaCorrente contaCorrente) {
			List<ContaCorrenteProdutoDTO> produtos = List.of();
			try {
				produtos = contaCorrenteProdutosFeignClients.contaCorrenteProdutos(contaCorrente.getId(), PageRequest.of(0, 10)).getContent()
						  .stream()
						  .flatMap(Collection<ContaCorrenteProdutoDTO>::stream)
						  .collect(Collectors.toList());
			} catch (Exception e) {
				LOG.error("O serviço [ds-conta-corrente-produtos] listar produtos vinculados a conta corrente não está respondendo.", e.getMessage());
			}
			
			return contaCorrenteMapper.buildContaCorrenteResponseDTO(contaCorrente)
					.withProdutos(produtos);
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
