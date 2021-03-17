package com.farias.banco.dspessoa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farias.banco.dspessoa.builder.PessoaBuilder;
import com.farias.banco.dspessoa.feignclients.ContaCorrenteFeignClients;
import com.farias.banco.dspessoa.model.Pessoa;
import com.farias.banco.dspessoa.repository.PessoaRepository;
import com.farias.banco.dspessoa.utils.ScoreUtils;

@Service
public class PessoaService {
	
	private final Logger LOG = LoggerFactory.getLogger(PessoaService.class);
	
	@Autowired
	private PessoaRepository repository;
	
	@Autowired
	private ContaCorrenteFeignClients contaCorrenteFeignClients;
	
	@Autowired
	private ScoreUtils scoreUtils;
	
	public Pessoa cadastrarPessoa(Pessoa pessoa) {
		
		Pessoa temp = new PessoaBuilder()
				.pessoa(pessoa)
				.score(scoreUtils)
				.builder();
		temp = repository.save(temp);

		try {
			contaCorrenteFeignClients.cadastarContaCorrente(temp);
		} catch (Exception e) {
			LOG.error("Erro ao conectar no Serviço [ds-conta-corrente] de abertura de conta corrente", e.getMessage() );
		}
		
		return pessoa;
	}
	
}
