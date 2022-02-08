package com.farias.banco.dscontacorrenteprodutos.broker.outbound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.farias.banco.dscontacorrenteprodutos.config.broker.BrokerOutput;
import com.farias.banco.dscontacorrenteprodutos.contants.BrokerConstants;
import com.farias.banco.dscontacorrenteprodutos.dto.PessoaContaCorrenteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@EnableBinding(BrokerOutput.class)
@RequiredArgsConstructor
public class ProdutosContaCorrenteBrokerOutbound {

	private final Logger LOG = LoggerFactory.getLogger(ProdutosContaCorrenteBrokerOutbound.class);

	private final BrokerOutput outbound;
	private final ObjectMapper objectMapper;

	public void publishProdutosContaCorrenteCreated(PessoaContaCorrenteDTO contaCorrente) {
		try {
			final var message = objectMapper.writeValueAsString(contaCorrente);
			this.outbound.publishProdutosContaCorrenteCreated().send(MessageBuilder.withPayload(message).build());
		} catch (Exception e) {
			String MESSAGE_ERROR = "Error ao publicar mensagem na exchange [{}]: [{}]";
			LOG.error(MESSAGE_ERROR, BrokerConstants.EXCHANGE_PRODUTOS_CONTA_CORRENTE_CREATED, e.getMessage() );
		}
	}
	
	
}
