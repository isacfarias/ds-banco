package com.farias.banco.dspessoa.broker.outbound;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.farias.banco.dspessoa.config.broker.BrokerOutput;
import com.farias.banco.dspessoa.constants.BrokerConstants;
import com.farias.banco.dspessoa.model.Pessoa;
import com.farias.banco.dspessoa.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@Transactional
@EnableBinding(BrokerOutput.class)
@RequiredArgsConstructor
public class PessoaBrokerOutbound {

	private final Logger LOG = LoggerFactory.getLogger(PessoaService.class);

	private final BrokerOutput outbound;
	private final ObjectMapper objectMapper;

	public void contaCorrentePublish(Pessoa pessoa) {
		try {
			final var message = objectMapper.writeValueAsString(pessoa);
			this.outbound.publishContaCorrenteCreated().send(MessageBuilder.withPayload(message).build());
		} catch (Exception e) {
			LOG.error("Error ao publicar mensagem na exchange :".concat(BrokerConstants.EXCHAGE_CONTA_CORRENTE_CREATED), e.getMessage() );
		}

	}
	
	
}
