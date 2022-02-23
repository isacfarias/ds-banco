package com.farias.banco.contacorrente.resources;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farias.banco.contacorrente.dto.ContaCorrenteResponseDTO;
import com.farias.banco.contacorrente.service.ContaCorrenteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = {"Conta corrente"})
@RestController
@RequestMapping(value = "/contacorrente")
@RequiredArgsConstructor
public class ContaCorrenteResource {

	private final ContaCorrenteService service;

	@ApiOperation(value = "Faz um get para retornar todas as contas corrente cadastradas", response = ContaCorrenteResponseDTO[].class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Em caso de sucesso.", response = ContaCorrenteResponseDTO.class)
	})
	@GetMapping()
	public Page<ContaCorrenteResponseDTO> contasCorrente(@RequestParam(required = false) Optional<String> agencia,
			@RequestParam(required = false) Optional<String> numero,
			@RequestParam(required = false) Optional<String> tipo,
			@RequestParam(required = false) Optional<Long> pessoaId,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size,
			@RequestParam(defaultValue = "agencia") String sort,
			@RequestParam(defaultValue = "ASC") Sort.Direction direction) {
				
		return service.contaCorrenteProdutos(agencia, numero, tipo, pessoaId, PageRequest.of(page, size, Sort.by(direction, sort)));
	}

}
