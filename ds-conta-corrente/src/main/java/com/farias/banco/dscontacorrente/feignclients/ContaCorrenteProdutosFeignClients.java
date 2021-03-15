package com.farias.banco.dscontacorrente.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.farias.banco.dscontacorrente.dto.PessoaContaCorrenteDTO;

@Component
@FeignClient(name="ds-conta-corrente-produtos", path = "/contacorrenteprodutos")
public interface ContaCorrenteProdutosFeignClients {

	@PostMapping
	ResponseEntity<Void> vincularProdutosContaCorrente(@RequestBody PessoaContaCorrenteDTO pessoaContaCorrente);
}
