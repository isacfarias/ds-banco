package com.farias.banco.dspessoa.broker.inbound;

import javax.transaction.Transactional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.farias.banco.dspessoa.config.broker.BrokerInput;
import com.farias.banco.dspessoa.constants.BrokerConstants;
import com.farias.banco.dspessoa.model.Pessoa;

import lombok.RequiredArgsConstructor;

@Component
@EnableBinding(BrokerInput.class)
@RequiredArgsConstructor
public class PessoaBrokerInboud {

	private final BrokerInput input;

	@StreamListener(target = BrokerConstants.EXCHAGE_CONTA_CORRENTE_PROCESSED)
	public void contaCorrentePrecessed(Pessoa pessoa) {
		
		System.out.println("-.");
		
		
	}
	

}
