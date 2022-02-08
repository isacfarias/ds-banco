package com.farias.banco.dscontacorrente.config.broker;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import com.farias.banco.dscontacorrente.constants.BrokerConstants;

public interface BrokerOutput {


	@Output(BrokerConstants.EXCHANGE_CONTA_CORRENTE_PROCESSED)
	MessageChannel publishContaCorrenteProcessed();
	
	@Output(BrokerConstants.EXCHANGE_PRODUTOS_CONTA_CORRENTE_CREATED)
	MessageChannel publishProdutosContaCorrenteCreated();

}
