package com.farias.banco.pessoa.modules.integration.broker.consumer;

import com.farias.banco.pessoa.constants.BrokerConstants;
import com.farias.banco.pessoa.dto.PessoaContaCorrenteDTO;
import com.farias.banco.pessoa.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j()
@Component
@RequiredArgsConstructor
public class ContaCorrenteProcessedConsumer implements Consumer<Message<String>> {

	private final ObjectMapper mapper;
	private final PessoaService service;

	@Override
	public void accept(Message<String> message) {
		log.info("Mensagem recebida na fila [{}] - headers [{}] - conteudo [{}]", BrokerConstants.Q_CONTA_CORRENTE_PROCESSED_ORIGE_NAME, message.getHeaders(), message.getPayload());
		try {
			log.info("[Start] - status atualizar conta corrente");
			final var contaCorrente = mapper.readValue(message.getPayload(), PessoaContaCorrenteDTO.class);
			service.atualizarContaCorrente(contaCorrente);
			log.info("[Stop] - status atualizar conta corrente");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

}
