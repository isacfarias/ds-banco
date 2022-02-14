package com.farias.banco.dspessoa.componente;

import org.springframework.messaging.MessageHeaders;

import lombok.Builder;
import lombok.Getter;
import lombok.With;

@With
@Builder
@Getter
public class Message implements org.springframework.messaging.Message<String> {
	
	private String payload;
	private MessageHeaders headers;

}
