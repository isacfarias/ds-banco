package com.farias.banco.dscontacorrente.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.farias.banco.dscontacorrente.config.FeignConfiguration;
import com.farias.banco.dscontacorrente.dto.ContaCorrenteProdutoDTO;
import com.farias.banco.dscontacorrente.dto.PessoaContaCorrenteDTO;

@Component
@FeignClient(name="ds-conta-corrente-produtos", path = "/contacorrenteprodutos", configuration = FeignConfiguration.class)
public interface ContaCorrenteProdutosFeignClients {

	@PostMapping
	ResponseEntity<Void> vincularProdutosContaCorrente(@RequestBody PessoaContaCorrenteDTO pessoaContaCorrente);
	
	@GetMapping("/contacorrente/{contaCorrenteId}")
	ResponseEntity<List<ContaCorrenteProdutoDTO>> contaCorrenteProdutos(@PathVariable Long contaCorrenteId);
}
