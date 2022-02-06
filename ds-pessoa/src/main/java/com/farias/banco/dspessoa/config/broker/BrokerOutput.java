package com.farias.banco.dspessoa.config.broker;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import com.farias.banco.dspessoa.constants.BrokerConstants;

public interface BrokerOutput {


	@Output(BrokerConstants.EXCHAGE_CONTA_CORRENTE_CREATED)
	MessageChannel publishContaCorrenteCreated();

}
