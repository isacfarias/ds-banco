package com.farias.banco.dsprodutos.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farias.banco.dsprodutos.dto.ProdutosDTO;
import com.farias.banco.dsprodutos.model.ProdutoFaixa;
import com.farias.banco.dsprodutos.repository.ProdutosFaixaRepository;

@RestController
@RequestMapping(value = "/produtosfaixa")
public class ProdutosFaixaResource {
	
	@Autowired
	private ProdutosFaixaRepository repository;

	@GetMapping
	public ResponseEntity<List<ProdutoFaixa>> produtosScore() {
		List<ProdutoFaixa> produtosScore = repository.findAll();
		return !produtosScore.isEmpty() ? ResponseEntity.ok(produtosScore) : ResponseEntity.notFound().build();		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoFaixa> produto(@PathVariable Integer id) {
		Optional<ProdutoFaixa> produtoScore = repository.findById(id);
		return produtoScore.isPresent() ? ResponseEntity.ok(produtoScore.get()) : ResponseEntity.notFound().build();			
	}
	
	@PostMapping
	public ResponseEntity<ProdutoFaixa> cadastrarProdutos(@RequestBody ProdutoFaixa produtoScore) {
		ProdutoFaixa temp = repository.save(produtoScore);		
		return ResponseEntity.ok(temp);
	}
	
	@GetMapping("/score/{score}")
	public ResponseEntity<List<ProdutosDTO>> produtosPorScore(@PathVariable Integer score) {
		List<ProdutosDTO> produtos = repository.findByScoreFaixa(score)
				                               .stream()
				                               .map( produtosScore -> new ProdutosDTO(produtosScore.getProdutoTipo().getId(), produtosScore.getValor()))
				                               .collect(Collectors.toList());
		
		return !produtos.isEmpty() ? ResponseEntity.ok(produtos) : ResponseEntity.notFound().build();		
	}
}