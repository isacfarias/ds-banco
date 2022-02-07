package com.farias.banco.dscontacorrenteprodutos.config.broker;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import com.farias.banco.dscontacorrenteprodutos.contants.BrokerConstants;


public interface BrokerInput {

	@Input(BrokerConstants.EXCHANGE_PRODUTOS_CONTA_CORRENTE_CREATED)
	MessageChannel subcribeContaCorrenteCreated();

}
