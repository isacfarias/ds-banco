package com.farias.banco.dscontacorrente.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farias.banco.dscontacorrente.dto.ContaCorrenteDTO;
import com.farias.banco.dscontacorrente.model.ContaCorrente;
import com.farias.banco.dscontacorrente.model.Pessoa;
import com.farias.banco.dscontacorrente.service.ContaCorrenteService;

@RestController
@RequestMapping(value = "/contacorrente")
public class ContaCorrenteResource {

	@Autowired
	private ContaCorrenteService service;

	@GetMapping()
	public ResponseEntity<List<ContaCorrenteDTO>> contasCorrente() {
		List<ContaCorrenteDTO> contaCorrenteComProdutos = service.contaCorrenteProdutos();
		return !contaCorrenteComProdutos.isEmpty() ? ResponseEntity.ok(contaCorrenteComProdutos) : ResponseEntity.noContent().build();
	}

	@GetMapping("/pessoa/{pessoaId}")
	public ResponseEntity<List<ContaCorrenteDTO>> contasCorrente(@PathVariable() Long pessoaId) {
		List<ContaCorrenteDTO> contaCorrenteComProdutos = service.searchContaCorrentePorPessoa(pessoaId);
		return !contaCorrenteComProdutos.isEmpty() ? ResponseEntity.ok(contaCorrenteComProdutos) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<ContaCorrente> cadastarContaCorrente(@RequestBody Pessoa pessoa) {
		ContaCorrente contaCorrente = service.cadastrarContaCorrente(pessoa);
		return ResponseEntity.ok(contaCorrente);
	}

}
