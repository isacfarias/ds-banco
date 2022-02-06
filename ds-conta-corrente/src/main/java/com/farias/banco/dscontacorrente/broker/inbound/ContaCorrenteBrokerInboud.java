package com.farias.banco.dscontacorrente.broker.inbound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.farias.banco.dscontacorrente.config.broker.BrokerInput;
import com.farias.banco.dscontacorrente.constants.BrokerConstants;
import com.farias.banco.dscontacorrente.model.Pessoa;
import com.farias.banco.dscontacorrente.service.ContaCorrenteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@EnableBinding(BrokerInput.class)
@RequiredArgsConstructor
public class ContaCorrenteBrokerInboud {

	private final Logger LOG = LoggerFactory.getLogger(ContaCorrenteBrokerInboud.class);


	private final BrokerInput input;
	private final ObjectMapper mapper;
	private final ContaCorrenteService service;

	@StreamListener(target = BrokerConstants.EXCHANGE_CONTA_CORRENTE_CREATED)
	public void contaCorrentePrecessed(String message) {
		LOG.info("Mensagem recebida na fila [{}] - conteudo [{}]", BrokerConstants.Q_CONTA_CORRENTE_CREATED_ORIGE_NAME, message);
		try {
			LOG.info("[Start] - cadastar conta corrente");
			final var pessoa = mapper.readValue(message, Pessoa.class);
			service.cadastrarContaCorrente(pessoa);
			LOG.info("[Stop] - cadastar conta corrente");	
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}


}
