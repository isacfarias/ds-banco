package com.farias.banco.dspessoa.broker.supplier;

import com.farias.banco.dspessoa.constants.BrokerConstants;
import com.farias.banco.dspessoa.model.Pessoa;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Slf4j
@Component
@RequiredArgsConstructor
public class PessoaMessageSupplier {

	private final ObjectMapper objectMapper;
	private final Sinks.Many<Message<String>> many;;

	public void contaCorrentePublish(Pessoa pessoa) {
		try {
			final var message = objectMapper.writeValueAsString(pessoa);
			many.emitNext(MessageBuilder.withPayload(message).build(), Sinks.EmitFailureHandler.FAIL_FAST);
		} catch (Exception e) {
			log.error("Error ao publicar mensagem na exchange [{}]: {}", BrokerConstants.EXCHANGE_CONTA_CORRENTE_CREATED, e.getMessage() );
		}
	}
	
	
}
