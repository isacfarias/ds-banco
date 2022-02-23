package com.farias.banco.contacorrenteprodutos.modules.config.broker;

import com.farias.banco.contacorrenteprodutos.modules.integration.broker.consumer.ProdutosContaCorrenteCreatedConsumer;
import com.farias.banco.contacorrenteprodutos.service.ContaCorrenteProdutosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MessageConsumer {


	private final ObjectMapper mapper;
	private final ContaCorrenteProdutosService service;

	@Bean
	public ProdutosContaCorrenteCreatedConsumer subscribeProdutosContaCorrenteCreated() {
		return new ProdutosContaCorrenteCreatedConsumer(mapper, service);
	}

}
