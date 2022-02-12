package com.farias.banco.dspessoa.broker.inbound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import com.farias.banco.dspessoa.config.broker.BrokerInput;
import com.farias.banco.dspessoa.constants.BrokerConstants;
import com.farias.banco.dspessoa.dto.PessoaContaCorrenteDTO;
import com.farias.banco.dspessoa.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@Component
@EnableBinding(BrokerInput.class)
@RequiredArgsConstructor
public class PessoaBrokerInboud {

	private final Logger LOG = LoggerFactory.getLogger(PessoaBrokerInboud.class);
	
	private final ObjectMapper mapper;
	private final PessoaService service;

	@StreamListener(target = BrokerConstants.EXCHANGE_CONTA_CORRENTE_PROCESSED)
	public void contaCorrentePrecessed(String message, @Headers Map<String, Object> headers) {
		LOG.info("Mensagem recebida na fila [{}] - headers [{}] - conteudo [{}]", BrokerConstants.Q_CONTA_CORRENTE_PROCESSED_ORIGE_NAME, headers, message);
		try {
			LOG.info("[Start] - status atualizar conta corrente");
			final var contaCorrente = mapper.readValue(message, PessoaContaCorrenteDTO.class);
			service.atualizarContaCorrente(contaCorrente);
			LOG.info("[Stop] - status atualizar conta corrente");	
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}
	
	@StreamListener(target = BrokerConstants.EXCHANGE_PRODUTOS_CONTA_CORRENTE_PROCESSED)
	public void produtosContaCorrentePrecessed(String message, @Headers Map<String, Object> headers) {
		LOG.info("Mensagem recebida na fila [{}] - headers [{}] - conteudo [{}]", BrokerConstants.Q_PRODUTOS_CONTA_CORRENTE_PROCESSED_ORIGE_NAME, headers, message);
		try {
			LOG.info("[Start] - atualizar status conta corrente produtos");
			final var contaCorrente = mapper.readValue(message, PessoaContaCorrenteDTO.class);
			service.atualizarContaCorrenteProdutos(contaCorrente);
			LOG.info("[Stop] - atualizar status conta corrente produtos");	
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}
	

}
