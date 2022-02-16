package com.farias.banco.dscontacorrente.modules.integration.broker.supplier;

import com.farias.banco.dscontacorrente.modules.integration.broker.supplier.impls.ContaCorrenteProcessedSupplier;
import com.farias.banco.dscontacorrente.modules.integration.broker.supplier.impls.ProdutosContaCorrenteCreatedSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.farias.banco.dscontacorrente.constants.BrokerConstants;
import com.farias.banco.dscontacorrente.dto.PessoaContaCorrenteResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ContaCorrenteMessageSupplier {

	private final Logger LOG = LoggerFactory.getLogger(ContaCorrenteMessageSupplier.class);
	private final String MESSAGE_ERROR = "Error ao publicar mensagem na exchange [{}]: [{}]";

	private final ContaCorrenteProcessedSupplier correnteProcessedSupplier;
	private final ProdutosContaCorrenteCreatedSupplier produtosContaCorrenteCreatedSupplier;
	private final ObjectMapper objectMapper;

	public void vincularContaCorrentePublish(PessoaContaCorrenteResponseDTO contaCorrente) {
		try {
			final var message = objectMapper.writeValueAsString(contaCorrente);
			this.correnteProcessedSupplier.publish(MessageBuilder.withPayload(message).build());
		} catch (Exception e) {
			LOG.error(MESSAGE_ERROR, BrokerConstants.EXCHANGE_CONTA_CORRENTE_PROCESSED, e.getMessage() );
		}
	}

	public void vincularProdutosContaCorrentePublish(PessoaContaCorrenteResponseDTO contaCorrente) {
		try {
			final var message = objectMapper.writeValueAsString(contaCorrente);
			this.produtosContaCorrenteCreatedSupplier.publish(MessageBuilder.withPayload(message).build());
		} catch (Exception e) {
			LOG.error(MESSAGE_ERROR, BrokerConstants.EXCHANGE_PRODUTOS_CONTA_CORRENTE_CREATED, e.getMessage() );
		}
	}
	
	
}
