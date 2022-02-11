package com.farias.banco.dsprodutos.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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
import com.farias.banco.dsprodutos.dto.ProdutoValorDTO;
import com.farias.banco.dsprodutos.service.ProdutosFaixaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = {"Produtos faixa"})
@RestController
@RequestMapping(value = "/produtosfaixa")
@RequiredArgsConstructor
public class ProdutosFaixaResource {

	private final ProdutosFaixaService service;

	
	@ApiOperation(value = "Faz um get para retornar todos as faixas de produtos cadastradas", response = ProdutoFaixaDTO[].class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Em caso de sucesso.", response = ProdutoFaixaDTO.class)
	})
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

	@ApiOperation(value = "Faz um post para cadastrar uma nova faixa de produto", response = ProdutoFaixaDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Em caso de sucesso.", response = ProdutoFaixaDTO.class),
			@ApiResponse(code = 400, message = "Em caso de body invalido.", response = String.class)
	})	    
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProdutoFaixaDTO cadastrarProdutos(@RequestBody ProdutoFaixaDTORequest produtoFaixa) {
		return service.save(produtoFaixa);
	}

	
	@ApiOperation(value = "Faz um get para retornar todas as faixas de produtos cadastradas, para um score especifico", response = ProdutoValorDTO[].class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Em caso de sucesso.", response = ProdutoValorDTO.class)
	})
	@GetMapping("/score/{score}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProdutoValorDTO> produtosPorScore(@PathVariable Integer score) {
		return service.findByScore(score);
	}
}