package com.farias.banco.dscontacorrenteprodutos.config.broker;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import com.farias.banco.dscontacorrenteprodutos.contants.BrokerConstants;

public interface BrokerOutput {

	
	@Output(BrokerConstants.EXCHANGE_PRODUTOS_CONTA_CORRENTE_PROCESSED)
	MessageChannel publishProdutosContaCorrenteProcessed();

}
