package com.farias.banco.dscontacorrenteprodutos.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farias.banco.dscontacorrenteprodutos.dto.PessoaContaCorrenteDTO;
import com.farias.banco.dscontacorrenteprodutos.service.ContaCorrenteProdutosService;

@RestController
@RequestMapping(value = "/contacorrenteprodutos")
public class ContaCorrenteProdutosResource {
	
	@Autowired
	private ContaCorrenteProdutosService service;
	
	@PostMapping
	public ResponseEntity<Void> vincularProdutosContaCorrente(@RequestBody PessoaContaCorrenteDTO pessoaContaCorrente) {
		service.vincularProdutosContaCorrente(pessoaContaCorrente);
		return ResponseEntity.noContent().build();
	}

}
