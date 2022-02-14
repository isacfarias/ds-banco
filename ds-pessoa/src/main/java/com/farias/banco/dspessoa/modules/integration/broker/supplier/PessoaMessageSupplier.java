package com.farias.banco.dspessoa.modules.integration.broker.supplier;

import com.farias.banco.dspessoa.constants.BrokerConstants;
import com.farias.banco.dspessoa.modules.integration.broker.supplier.impls.ContaCorrenteCreatedSupplier;
import com.farias.banco.dspessoa.modules.model.Pessoa;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PessoaMessageSupplier {

	private final ObjectMapper objectMapper;
	private final ContaCorrenteCreatedSupplier supplier;

	public void contaCorrentePublish(Pessoa pessoa) {
		try {
			final var message = objectMapper.writeValueAsString(pessoa);
			supplier.publish(MessageBuilder.withPayload(message).build());
		} catch (Exception e) {
			log.error("Error ao publicar message na exchange [{}]: {}", BrokerConstants.EXCHANGE_CONTA_CORRENTE_CREATED, e.getMessage() );
		}
	}

}
