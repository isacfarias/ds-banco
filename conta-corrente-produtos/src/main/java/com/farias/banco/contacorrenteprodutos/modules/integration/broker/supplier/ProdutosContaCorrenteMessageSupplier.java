package com.farias.banco.contacorrenteprodutos.modules.integration.broker.supplier;

import com.farias.banco.contacorrenteprodutos.contants.BrokerConstants;
import com.farias.banco.contacorrenteprodutos.dto.PessoaContaCorrenteDTO;
import com.farias.banco.contacorrenteprodutos.modules.integration.broker.supplier.impls.ProdutosContaCorrenteProcessedSupplier;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutosContaCorrenteMessageSupplier {

	private final Logger LOG = LoggerFactory.getLogger(ProdutosContaCorrenteMessageSupplier.class);

	private final ProdutosContaCorrenteProcessedSupplier supplier;
	private final ObjectMapper objectMapper;

	public void publishProdutosContaCorrenteProcessed(PessoaContaCorrenteDTO contaCorrente) {
		try {
			final var message = objectMapper.writeValueAsString(contaCorrente);
			this.supplier.publish(MessageBuilder.withPayload(message).build());
		} catch (Exception e) {
			String MESSAGE_ERROR = "Error ao publicar mensagem na exchange [{}]: [{}]";
			LOG.error(MESSAGE_ERROR, BrokerConstants.EXCHANGE_PRODUTOS_CONTA_CORRENTE_CREATED, e.getMessage() );
		}
	}
	
	
}
