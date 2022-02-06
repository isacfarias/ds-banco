package com.farias.banco.dscontacorrente.config.broker;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import com.farias.banco.dscontacorrente.constants.BrokerConstants;

public interface BrokerInput {

	@Input(BrokerConstants.EXCHANGE_CONTA_CORRENTE_CREATED)
	MessageChannel subcribeContaCorrenteCreated();

}
