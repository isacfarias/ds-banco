package com.farias.banco.dscontacorrenteprodutos.broker.inbound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.farias.banco.dscontacorrenteprodutos.config.broker.BrokerInput;
import com.farias.banco.dscontacorrenteprodutos.contants.BrokerConstants;
import com.farias.banco.dscontacorrenteprodutos.dto.PessoaContaCorrenteDTO;
import com.farias.banco.dscontacorrenteprodutos.model.Pessoa;
import com.farias.banco.dscontacorrenteprodutos.service.ContaCorrenteProdutosService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@EnableBinding(BrokerInput.class)
@RequiredArgsConstructor
public class ProdutosContaCorrenteBrokerInboud {

	private final Logger LOG = LoggerFactory.getLogger(ProdutosContaCorrenteBrokerInboud.class);


	private final BrokerInput input;
	private final ObjectMapper mapper;
	private final ContaCorrenteProdutosService service;
	
	@StreamListener(target = BrokerConstants.EXCHANGE_PRODUTOS_CONTA_CORRENTE_CREATED)
	public void contaCorrentePrecessed(String message) {
		LOG.info("Mensagem recebida na fila [{}] - conteudo [{}]", BrokerConstants.Q_PRODUTOS_CONTA_CORRENTE_CREATED_ORIGE_NAME, message);
		try {
			LOG.info("[Start] - cadastar conta corrente");
			final var pessoaContaCorrente = mapper.readValue(message, PessoaContaCorrenteDTO.class);
			service.vincularProdutosContaCorrente(pessoaContaCorrente);
			LOG.info("[Stop] - cadastar conta corrente");	
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}


}
