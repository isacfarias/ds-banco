package com.farias.banco.dscontacorrente.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farias.banco.dscontacorrente.builder.ContaCorrenteBuilder;
import com.farias.banco.dscontacorrente.config.AppConfig;
import com.farias.banco.dscontacorrente.dto.ContaCorrenteDTO;
import com.farias.banco.dscontacorrente.dto.ContaCorrenteProdutoDTO;
import com.farias.banco.dscontacorrente.dto.PessoaContaCorrenteDTO;
import com.farias.banco.dscontacorrente.feignclients.ContaCorrenteProdutosFeignClients;
import com.farias.banco.dscontacorrente.model.ContaCorrente;
import com.farias.banco.dscontacorrente.model.Pessoa;
import com.farias.banco.dscontacorrente.repository.ContaCorrenteRepository;
import com.farias.banco.dscontacorrente.utils.ContaCorrenteNumeroUtils;

@Service
public class ContaCorrenteService {

	private final Logger LOG = LoggerFactory.getLogger(ContaCorrenteService.class);

	@Autowired
	private AppConfig config;

	@Autowired
	private ContaCorrenteRepository repository;

	@Autowired
	private ContaCorrenteProdutosFeignClients contaCorrenteProdutosFeignClients;

	@Autowired
	private ContaCorrenteNumeroUtils contaNumero;

	public ContaCorrente cadastrarContaCorrente(Pessoa pessoa) {
		ContaCorrente contacorrente = new ContaCorrenteBuilder()
				.agencia(config.getAgencia().getNumero())
				.numero(contaNumero())
				.pessoa(pessoa.getId())
				.contaTipo(pessoa.getTipo())
				.builder();

		contacorrente = repository.save(contacorrente);
		try {
			contaCorrenteProdutosFeignClients.vincularProdutosContaCorrente(new PessoaContaCorrenteDTO(pessoa.getId(), contacorrente.getId(), pessoa.getScore()));
		} catch (Exception e) {
			LOG.error("O serviço [ds-conta-corrente-produtos] de vincular produtos esta Off.", e.getMessage());
		}

		return contacorrente;
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

	public List<ContaCorrenteDTO> searchContaCorrentePorPessoa(Long pessoaId) {
		List<ContaCorrente> contasCorrente = repository.findByPessoa(pessoaId);
		return contaCorrenteProdutos(contasCorrente);
	}

	public List<ContaCorrenteDTO> contaCorrenteProdutos() {
		List<ContaCorrente> contasCorrente = repository.findAll();
		return contaCorrenteProdutos(contasCorrente);
	}

	private List<ContaCorrenteDTO> contaCorrenteProdutos(List<ContaCorrente> contasCorrente) {
		List<ContaCorrenteDTO> contaCorrenteList = new ArrayList<>();
		for (ContaCorrente contaCorrente : contasCorrente) {
			try {
				List<ContaCorrenteProdutoDTO> produtos = contaCorrenteProdutosFeignClients.contaCorrenteProdutos(contaCorrente.getId()).getBody();
				contaCorrenteList.add(new ContaCorrenteDTO(contaCorrente.getAgencia(), contaCorrente.getNumero(), contaCorrente.getTipo().name(), produtos));
			} catch (Exception e) {
				LOG.error("O serviço [ds-conta-corrente-produtos] listar produtos vinculados a conta corrente não está respondendo.", e.getMessage());
			}
		}
		return contaCorrenteList;
	}
}
