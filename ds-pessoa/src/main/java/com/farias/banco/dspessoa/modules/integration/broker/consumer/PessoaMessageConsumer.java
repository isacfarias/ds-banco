package com.farias.banco.dspessoa.modules.integration.broker.consumer;

import com.farias.banco.dspessoa.constants.BrokerConstants;
import com.farias.banco.dspessoa.dto.PessoaContaCorrenteDTO;
import com.farias.banco.dspessoa.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j()
@Component
@RequiredArgsConstructor
public class PessoaMessageConsumer {

	private final ObjectMapper mapper;
	private final PessoaService service;

	@Bean
	public Consumer<Message<String>> subscribedContaCorrenteProcessed() {
		return message -> {
			log.info("Mensagem recebida na fila [{}] - headers [{}] - conteudo [{}]", BrokerConstants.Q_CONTA_CORRENTE_PROCESSED_ORIGE_NAME, message.getHeaders(), message.getPayload());
			try {
				log.info("[Start] - status atualizar conta corrente");
				final var contaCorrente = mapper.readValue(message.getPayload(), PessoaContaCorrenteDTO.class);
				service.atualizarContaCorrente(contaCorrente);
				log.info("[Stop] - status atualizar conta corrente");
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		};
	}

	@Bean
	public Consumer<Message<String>> subscribedProdutosContaCorrenteProcessed() {
		return message -> {
			log.info("Mensagem recebida na fila [{}] - headers [{}] - conteudo [{}]", BrokerConstants.Q_PRODUTOS_CONTA_CORRENTE_PROCESSED_ORIGE_NAME, message.getHeaders(), message.getPayload());
			try {
				log.info("[Start] - atualizar status conta corrente produtos");
				final var contaCorrente = mapper.readValue(message.getPayload(), PessoaContaCorrenteDTO.class);
				service.atualizarContaCorrenteProdutos(contaCorrente);
				log.info("[Stop] - atualizar status conta corrente produtos");
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		} ;
	}
}
