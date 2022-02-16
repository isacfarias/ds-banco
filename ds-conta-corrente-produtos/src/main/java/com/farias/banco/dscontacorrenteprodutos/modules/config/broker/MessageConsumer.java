package com.farias.banco.dscontacorrenteprodutos.modules.config.broker;

import com.farias.banco.dscontacorrenteprodutos.modules.integration.broker.consumer.ProdutosContaCorrenteCreatedConsumer;
import com.farias.banco.dscontacorrenteprodutos.service.ContaCorrenteProdutosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MessageConsumer {


	private final ObjectMapper mapper;
	private final ContaCorrenteProdutosService service;

	public ProdutosContaCorrenteCreatedConsumer subcribeContaCorrenteCreated() {
		return new ProdutosContaCorrenteCreatedConsumer(mapper, service);
	}

}
