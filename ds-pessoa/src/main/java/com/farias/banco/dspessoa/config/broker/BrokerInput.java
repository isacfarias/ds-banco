package com.farias.banco.dspessoa.config.broker;

import org.springframework.cloud.stream.annotation.Input;

import com.farias.banco.dspessoa.constants.BrokerConstants;
import org.springframework.messaging.SubscribableChannel;

public interface BrokerInput {

	@Input(BrokerConstants.EXCHANGE_CONTA_CORRENTE_PROCESSED)
	SubscribableChannel subscribedContaCorrenteProcessed();
	
	@Input(BrokerConstants.EXCHANGE_PRODUTOS_CONTA_CORRENTE_PROCESSED)
	SubscribableChannel subscribedProdutosContaCorrenteProcessed();

}
