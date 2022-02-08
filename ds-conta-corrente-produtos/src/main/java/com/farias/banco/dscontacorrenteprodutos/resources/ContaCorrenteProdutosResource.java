 package com.farias.banco.dscontacorrenteprodutos.resources;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farias.banco.dscontacorrenteprodutos.dto.ContaCorrenteProdutoDTO;
import com.farias.banco.dscontacorrenteprodutos.service.ContaCorrenteProdutosService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/contacorrenteprodutos")
public class ContaCorrenteProdutosResource {
	
	private final ContaCorrenteProdutosService service;

	@GetMapping
	public Page<ContaCorrenteProdutoDTO> findAll(@RequestParam(required = false) Optional<Long> contaCorrenteProdutosId,
			@RequestParam(required = false) Optional<Long> contaCorrente,
			@RequestParam(required = false) Optional<Integer> ativo,
			@RequestParam(required = false) Optional<Long> produtoTipo,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size,
			@RequestParam(defaultValue = "contaCorrente") String sort,
			@RequestParam(defaultValue = "ASC") Sort.Direction direction) {
		
		return service.findAll(contaCorrenteProdutosId, contaCorrente, ativo, produtoTipo, PageRequest.of(page, size, Sort.by(direction, sort)));
	}

}
