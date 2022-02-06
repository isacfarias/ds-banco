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

import com.farias.banco.dspessoa.dto.PessoaDTORequest;
import com.farias.banco.dspessoa.dto.PessoaDTOResponse;
import com.farias.banco.dspessoa.service.PessoaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/pessoa")
@RequiredArgsConstructor
public class PessoaResource {

	private final PessoaService service;

	@GetMapping
	public Page<PessoaDTOResponse> pessoas(@RequestParam(required = false) Optional<String> nome,
			@RequestParam(required = false) Optional<String> tipo,
			@RequestParam(required = false) Optional<Integer> score,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size,
			@RequestParam(defaultValue = "nome") String sort,
			@RequestParam(defaultValue = "ASC") Sort.Direction direction) {
		return service.findAll(nome, tipo, score, PageRequest.of(page, size, Sort.by(direction, sort)));
	}

	@PostMapping
	public ResponseEntity<PessoaDTOResponse> cadastrarPessoa(@Validated @RequestBody PessoaDTORequest pessoaRequest) {
		return ResponseEntity.ok(service.cadastrarPessoa(pessoaRequest));
	}
}
