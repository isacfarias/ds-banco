package com.farias.banco.dsprodutos.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farias.banco.dsprodutos.dto.ProdutoFaixaDTO;
import com.farias.banco.dsprodutos.dto.ProdutoFaixaDTORequest;
import com.farias.banco.dsprodutos.dto.ProdutosTipoDTO;
import com.farias.banco.dsprodutos.model.ProdutoFaixa;
import com.farias.banco.dsprodutos.repository.ProdutosFaixaRepository;
import com.farias.banco.dsprodutos.service.ProdutosFaixaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/produtosfaixa")
public class ProdutosFaixaResource {

	private final ProdutosFaixaService service;

	@GetMapping
	public Page<ProdutoFaixaDTO> produtosScore(@RequestParam(required = false) Optional<Long> id,
			@RequestParam(required = false) Optional<Long> produtoTipo,
			@RequestParam(required = false) Optional<Long> scoreMin,
			@RequestParam(required = false) Optional<Long> scoreMax,
			@RequestParam(required = false) Optional<Long> valor,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size,
			@RequestParam(defaultValue = "produtoTipo") String sort,
			@RequestParam(defaultValue = "ASC") Sort.Direction direction) {
		return service.findAll(id, produtoTipo, scoreMin, scoreMax, valor, PageRequest.of(page, size, Sort.by(direction, sort)));		
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProdutoFaixaDTO cadastrarProdutos(@RequestBody ProdutoFaixaDTORequest produtoFaixa) {
		return service.save(produtoFaixa);
	}

	@GetMapping("/score/{score}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProdutoFaixaDTO> produtosPorScore(@PathVariable Integer score) {
		return service.findByScore(score);
	}
}