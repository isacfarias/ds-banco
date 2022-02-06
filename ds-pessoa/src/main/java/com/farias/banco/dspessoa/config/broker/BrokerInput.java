package com.farias.banco.dspessoa.config.broker;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

import com.farias.banco.dspessoa.constants.BrokerConstants;

public interface BrokerInput {

	@Input(BrokerConstants.EXCHAGE_CONTA_CORRENTE_PROCESSED)
	MessageChannel subcribeContaCorrenteProcessed();

}
