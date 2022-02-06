package com.farias.banco.dspessoa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.farias.banco.dspessoa.broker.outbound.PessoaBrokerOutbound;
import com.farias.banco.dspessoa.builder.PessoaBuilder;
import com.farias.banco.dspessoa.feignclients.ContaCorrenteFeignClients;
import com.farias.banco.dspessoa.model.Pessoa;
import com.farias.banco.dspessoa.repository.PessoaRepository;
import com.farias.banco.dspessoa.utils.ScoreUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaService {
	
	private final Logger LOG = LoggerFactory.getLogger(PessoaService.class);
	
	private final PessoaRepository repository;
	private final ContaCorrenteFeignClients contaCorrenteFeignClients;
	private final ScoreUtils scoreUtils;
	private final PessoaBrokerOutbound brokerOutbound;
	
	public Pessoa cadastrarPessoa(Pessoa pessoa) {
		
		Pessoa temp = new PessoaBuilder()
				.pessoa(pessoa)
				.score(scoreUtils)
				.builder();

		repository.save(temp);

		try {
			brokerOutbound.contaCorrentePublish(pessoa);
		} catch (Exception e) {
			LOG.error("Erro ao conectar no Serviço [ds-conta-corrente] de abertura de conta corrente", e.getMessage() );
		}
		
		return pessoa;
	}
	
}
