package com.farias.banco.dspessoa.config.broker;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

import com.farias.banco.dspessoa.constants.BrokerConstants;

public interface BrokerInput {

	@Input(BrokerConstants.EXCHANGE_CONTA_CORRENTE_PROCESSED)
	MessageChannel subcribeContaCorrenteProcessed();
	
	@Input(BrokerConstants.EXCHANGE_PRODUTOS_CONTA_CORRENTE_PROCESSED)
	MessageChannel subcribeProdutosContaCorrenteProcessed();

}
