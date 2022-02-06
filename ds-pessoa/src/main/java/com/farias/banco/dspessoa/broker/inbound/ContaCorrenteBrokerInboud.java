package com.farias.banco.dspessoa.broker.inbound;

import javax.transaction.Transactional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Component;

import com.farias.banco.dspessoa.config.broker.BrokerInput;
import com.farias.banco.dspessoa.constants.BrokerConstants;
import com.farias.banco.dspessoa.model.Pessoa;

import lombok.RequiredArgsConstructor;

@Component
@Transactional
@EnableBinding(BrokerInput.class)
@RequiredArgsConstructor
public class ContaCorrenteBrokerInboud {

	private final BrokerInput input;

	@RabbitListener(queues =  BrokerConstants.Q_CONTA_CORRENTE_PROCESSED_ORIGE_NAME)
	public void contaCorrentePrecessed(Pessoa pessoa) {
		
		System.out.println("-.");
		
		
	}
	

}
