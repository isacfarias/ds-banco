package com.farias.banco.dspessoa.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farias.banco.dspessoa.model.Pessoa;
import com.farias.banco.dspessoa.repository.PessoaRepository;
import com.farias.banco.dspessoa.service.PessoaService;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaResource {
	
	
	@Autowired
	private PessoaService service;
	
	@Autowired
	private PessoaRepository repository;
	
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> pessoas() {
		List<Pessoa> pessoas = repository.findAll();
		return ResponseEntity.ok(pessoas);
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> cadastrarPessoa(@Validated @RequestBody Pessoa pessoa) {
		Pessoa pes = service.cadastrarPessoa(pessoa);
		return ResponseEntity.ok(pes);
	}
	

}
