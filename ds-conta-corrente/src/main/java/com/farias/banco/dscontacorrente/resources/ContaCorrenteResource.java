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

import com.farias.banco.dscontacorrente.model.ContaCorrente;
import com.farias.banco.dscontacorrente.model.Pessoa;
import com.farias.banco.dscontacorrente.repository.ContaCorrenteRepository;
import com.farias.banco.dscontacorrente.service.ContaCorrenteService;

@RestController
@RequestMapping(value = "/contacorrente")
public class ContaCorrenteResource {

	@Autowired
	private ContaCorrenteService service;
	
	@Autowired
	private ContaCorrenteRepository repository;
	
	@PostMapping
	public ResponseEntity<ContaCorrente> cadastarContaCorrente(@RequestBody Pessoa pessoa) {
		ContaCorrente contaCorrente = service.cadastrarContaCorrente(pessoa);
		return ResponseEntity.ok(contaCorrente);
	}
	
	@GetMapping("/pessoa/{id}")
	public ResponseEntity<List<ContaCorrente>> contasCorrente(@PathVariable Long id) {
		List<ContaCorrente> list = repository.findByPessoa(id);
		return ResponseEntity.ok(list);
	}
	
}
