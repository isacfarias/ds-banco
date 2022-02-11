package com.farias.banco.dspessoa.resource;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farias.banco.dspessoa.dto.PessoaRequestDTO;
import com.farias.banco.dspessoa.dto.PessoaResponseDTO;
import com.farias.banco.dspessoa.service.PessoaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = {"Pessoa"})
@RestController
@RequestMapping(value = "/pessoa")
@RequiredArgsConstructor
public class PessoaResource {

	private final PessoaService service;

	@ApiOperation(value = "Faz um get para retornar todas as pessoas cadastradas", response = PessoaResponseDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Em caso de sucesso.", response = String.class)
	})
	@GetMapping
	public Page<PessoaResponseDTO> pessoas(@RequestParam(required = false) Optional<String> nome,
			@RequestParam(required = false) Optional<String> tipo,
			@RequestParam(required = false) Optional<Integer> score,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size,
			@RequestParam(defaultValue = "nome") String sort,
			@RequestParam(defaultValue = "ASC") Sort.Direction direction) {
		return service.findAll(nome, tipo, score, PageRequest.of(page, size, Sort.by(direction, sort)));
	}

	
	@ApiOperation(value = "Faz um post para cadastrar uma pessoa", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Em caso de sucesso.", response = String.class),
			@ApiResponse(code = 400, message = "Em caso de body invalido.", response = String.class)
	})
	@PostMapping
	public ResponseEntity<PessoaResponseDTO> cadastrarPessoa(@Validated @RequestBody PessoaRequestDTO pessoaRequest) {
		return ResponseEntity.ok(service.cadastrarPessoa(pessoaRequest));
	}
}
