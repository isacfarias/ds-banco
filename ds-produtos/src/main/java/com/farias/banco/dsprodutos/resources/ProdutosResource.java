package com.farias.banco.dsprodutos.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farias.banco.dsprodutos.model.ProdutoTipo;
import com.farias.banco.dsprodutos.repository.ProdutosTipoRepository;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutosResource {
	
	@Autowired
	private ProdutosTipoRepository repository;

	@GetMapping
	public ResponseEntity<List<ProdutoTipo>> produtos() {
		List<ProdutoTipo> produtosTipo = repository.findAll();
		return !produtosTipo.isEmpty() ? ResponseEntity.ok(produtosTipo) : ResponseEntity.notFound().build();		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoTipo> produto(@PathVariable Integer id) {
		Optional<ProdutoTipo> produtosTipo = repository.findById(id);
		return produtosTipo.isPresent() ? ResponseEntity.ok(produtosTipo.get()) : ResponseEntity.notFound().build();		
	}
	
	@PostMapping
	public ResponseEntity<ProdutoTipo> cadastrarProdutos(@RequestBody ProdutoTipo produtoTipo) {
		ProdutoTipo temp = repository.save(produtoTipo);
		return ResponseEntity.ok(temp);		
	}
}