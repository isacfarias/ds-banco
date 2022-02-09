package com.farias.banco.dsprodutos.resources;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farias.banco.dsprodutos.dto.ProdutosTipoDTO;
import com.farias.banco.dsprodutos.model.ProdutoTipo;
import com.farias.banco.dsprodutos.service.ProdutosTipoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/produtos")
@RequiredArgsConstructor
public class ProdutosResource {

	private final ProdutosTipoService service;

	@GetMapping
	public Page<ProdutosTipoDTO> produtos(@RequestParam(required = false) Optional<String> descricao,
			@RequestParam(required = false) Optional<Long> produtoTipoId,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size,
			@RequestParam(defaultValue = "descricao") String sort,
			@RequestParam(defaultValue = "ASC") Sort.Direction direction) {
		return service.findAll(descricao, produtoTipoId, PageRequest.of(page, size, Sort.by(direction, sort)));		
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProdutosTipoDTO cadastrarProdutos(@RequestBody ProdutoTipo produtoTipo) {
		return service.save(produtoTipo);		
	}
}