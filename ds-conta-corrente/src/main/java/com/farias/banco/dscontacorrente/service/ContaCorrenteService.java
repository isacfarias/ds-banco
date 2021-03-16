package com.farias.banco.dscontacorrente.service;

import java.util.ArrayList;
import java.util.List;

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
		
		contaCorrenteProdutosFeignClients.vincularProdutosContaCorrente(new PessoaContaCorrenteDTO(pessoa.getId(), contacorrente.getId(), pessoa.getScore()));

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
		List<ContaCorrenteDTO> contaCorrenteList = new ArrayList<>();
		List<ContaCorrente> contasCorrente = repository.findByPessoa(pessoaId);
		
		for (ContaCorrente contaCorrente : contasCorrente) {
			List<ContaCorrenteProdutoDTO> produtos = contaCorrenteProdutosFeignClients.contaCorrenteProdutos(contaCorrente.getId()).getBody();
			contaCorrenteList.add(new ContaCorrenteDTO(contaCorrente.getAgencia(), contaCorrente.getNumero(), contaCorrente.getTipo().name(), produtos));
		}
		
		return contaCorrenteList;
	}
}
