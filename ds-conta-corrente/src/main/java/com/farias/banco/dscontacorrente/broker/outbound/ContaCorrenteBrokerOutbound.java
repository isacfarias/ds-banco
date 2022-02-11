package com.farias.banco.dscontacorrente.broker.outbound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.farias.banco.dscontacorrente.config.broker.BrokerOutput;
import com.farias.banco.dscontacorrente.constants.BrokerConstants;
import com.farias.banco.dscontacorrente.dto.PessoaContaCorrenteResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@EnableBinding(BrokerOutput.class)
@RequiredArgsConstructor
public class ContaCorrenteBrokerOutbound {

	private final Logger LOG = LoggerFactory.getLogger(ContaCorrenteBrokerOutbound.class);
	private final String MESSAGE_ERROR = "Error ao publicar mensagem na exchange [{}]: [{}]";

	private final BrokerOutput outbound;
	private final ObjectMapper objectMapper;

	public void vincularContaCorrentePublish(PessoaContaCorrenteResponseDTO contaCorrente) {
		try {
			final var message = objectMapper.writeValueAsString(contaCorrente);
			this.outbound.publishContaCorrenteProcessed().send(MessageBuilder.withPayload(message).build());
		} catch (Exception e) {
			LOG.error(MESSAGE_ERROR, BrokerConstants.EXCHANGE_CONTA_CORRENTE_PROCESSED, e.getMessage() );
		}
	}

	public void vincularProdutosContaCorrentePublish(PessoaContaCorrenteResponseDTO contaCorrente) {
		try {
			final var message = objectMapper.writeValueAsString(contaCorrente);
			this.outbound.publishProdutosContaCorrenteCreated().send(MessageBuilder.withPayload(message).build());
		} catch (Exception e) {
			LOG.error(MESSAGE_ERROR, BrokerConstants.EXCHANGE_PRODUTOS_CONTA_CORRENTE_CREATED, e.getMessage() );
		}
	}
	
	
}
