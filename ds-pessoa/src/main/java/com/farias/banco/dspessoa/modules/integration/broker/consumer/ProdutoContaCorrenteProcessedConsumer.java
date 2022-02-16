package com.farias.banco.dspessoa.modules.integration.broker.consumer;

import com.farias.banco.dspessoa.constants.BrokerConstants;
import com.farias.banco.dspessoa.dto.PessoaContaCorrenteDTO;
import com.farias.banco.dspessoa.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j()
@Component
@RequiredArgsConstructor
public class ProdutoContaCorrenteProcessedConsumer implements Consumer<Message<String>> {

	private final ObjectMapper mapper;
	private final PessoaService service;

	@Override
	public void accept(Message<String> message) {
		log.info("Mensagem recebida na fila [{}] - headers [{}] - conteudo [{}]", BrokerConstants.Q_PRODUTOS_CONTA_CORRENTE_PROCESSED_ORIGE_NAME, message.getHeaders(), message.getPayload());
		try {
			log.info("[Start] - atualizar status conta corrente produtos");
			final var contaCorrente = mapper.readValue(message.getPayload(), PessoaContaCorrenteDTO.class);
			service.atualizarContaCorrenteProdutos(contaCorrente);
			log.info("[Stop] - atualizar status conta corrente produtos");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

}
