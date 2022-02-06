package com.farias.banco.dscontacorrente.broker.outbound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.farias.banco.dscontacorrente.config.broker.BrokerOutput;
import com.farias.banco.dscontacorrente.constants.BrokerConstants;
import com.farias.banco.dscontacorrente.dto.PessoaContaCorrenteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@EnableBinding(BrokerOutput.class)
@RequiredArgsConstructor
public class ContaCorrenteBrokerOutbound {

	private final Logger LOG = LoggerFactory.getLogger(ContaCorrenteBrokerOutbound.class);

	private final BrokerOutput outbound;
	private final ObjectMapper objectMapper;

	public void vincularProdutosContaCorrentePublish(PessoaContaCorrenteDTO contaCorrente) {
		try {
			final var message = objectMapper.writeValueAsString(contaCorrente);
			this.outbound.publishContaCorrenteCreated().send(MessageBuilder.withPayload(message).build());
		} catch (Exception e) {
			LOG.error("Error ao publicar mensagem na exchange [{}]: [{}]", BrokerConstants.EXCHANGE_PRODUTOS_CONTA_CORRENTE_CREATED, e.getMessage() );
		}
	}
	
	
}
