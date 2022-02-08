package com.farias.banco.dscontacorrente.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.farias.banco.dscontacorrente.dto.ContaCorrenteProdutoDTO;

@Component
@FeignClient(name="ds-conta-corrente-produtos", path = "/contacorrenteprodutos")
public interface ContaCorrenteProdutosFeignClients {

	
	@GetMapping("/contacorrente")
	ResponseEntity<List<ContaCorrenteProdutoDTO>> contaCorrenteProdutos(@RequestParam Long contaCorrente);
}
