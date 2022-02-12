package com.farias.banco.dspessoa.config.broker;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import com.farias.banco.dspessoa.constants.BrokerConstants;

/**
 * Referencias
 *  https://docs.spring.io/spring-cloud-stream/docs/Brooklyn.RELEASE/reference/html/_getting_started.html
 */

public interface BrokerOutput {


	@Output(BrokerConstants.EXCHANGE_CONTA_CORRENTE_CREATED)
	MessageChannel publishContaCorrenteCreated();

}
