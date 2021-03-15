package com.farias.banco.dscontacorrente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farias.banco.dscontacorrente.builder.ContaCorrenteBuilder;
import com.farias.banco.dscontacorrente.config.AppConfig;
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
	private ContaCorrenteNumeroUtils contaNumero;

	public ContaCorrente cadastrarContaCorrente(Pessoa pessoa) {
		ContaCorrente contacorrente = new ContaCorrenteBuilder()
				.agencia(config.getAgencia().getNumero())
				.numero(contaNumero())
				.pessoa(pessoa.getId())
				.contaTipo(pessoa.getTipo())
				.builder();

		return repository.save(contacorrente);
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

}
