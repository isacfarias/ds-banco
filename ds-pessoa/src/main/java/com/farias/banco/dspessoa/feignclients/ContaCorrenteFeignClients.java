package com.farias.banco.dspessoa.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.farias.banco.dspessoa.model.ContaCorrente;
import com.farias.banco.dspessoa.model.Pessoa;

@Component
@FeignClient(name="ds-conta-corrente", path = "/contacorrente")
public interface ContaCorrenteFeignClients {

	@PostMapping
	public ResponseEntity<ContaCorrente> cadastarContaCorrente(@RequestBody Pessoa pessoa);
}
