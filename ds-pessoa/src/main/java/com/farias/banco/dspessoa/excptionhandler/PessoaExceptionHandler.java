package com.farias.banco.dspessoa.excptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PessoaExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource message;
	
}
