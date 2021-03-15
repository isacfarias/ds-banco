package com.farias.banco.dsprodutos.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farias.banco.dsprodutos.model.ProdutoScore;
import com.farias.banco.dsprodutos.repository.ProdutosScoreRepository;

@RestController
@RequestMapping(value = "/produtosscore")
public class ProdutosScoreResource {
	
	@Autowired
	private ProdutosScoreRepository repository;

	@GetMapping
	public ResponseEntity<List<ProdutoScore>> produtosScore() {
		List<ProdutoScore> produtosScore = repository.findAll();
		return ResponseEntity.ok(produtosScore);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoScore> produto(@PathVariable Integer id) {
		ProdutoScore produtoScore = repository.findById(id).get();
		return ResponseEntity.ok(produtoScore);		
	}
	
	@PostMapping
	public ResponseEntity<ProdutoScore> cadastrarProdutos(@RequestBody ProdutoScore produtoScore) {
		ProdutoScore temp = repository.save(produtoScore);		
		return ResponseEntity.ok(temp);
	}
	
	@GetMapping("/score/{score}")
	public ResponseEntity<List<ProdutoScore>> produtosPorScore(@PathVariable Integer score) {
		List<ProdutoScore> produtosScore = repository.findByScoreMinGreaterThanEqualAndScoreMaxLessThanEqual(score, score);
		return ResponseEntity.ok(produtosScore);		
	}
	
}

