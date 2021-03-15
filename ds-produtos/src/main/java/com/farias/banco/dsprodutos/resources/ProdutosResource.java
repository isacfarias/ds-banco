package com.farias.banco.dsprodutos.resources;

import java.util.List;

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
	
	private ProdutosTipoRepository repository;

	@GetMapping
	public ResponseEntity<List<ProdutoTipo>> produtos() {
		List<ProdutoTipo> produtosTipo = repository.findAll();
		return ResponseEntity.ok(produtosTipo);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoTipo> produto(@PathVariable Integer id) {
		ProdutoTipo produtosTipo = repository.findById(id).get();
		return ResponseEntity.ok(produtosTipo);		
	}
	
	@PostMapping
	public ResponseEntity<ProdutoTipo> cadastrarProdutos(@RequestBody ProdutoTipo produtoTipo) {
		ProdutoTipo temp = repository.save(produtoTipo);
		return ResponseEntity.ok(temp);		
	}
	
}

