package com.farias.banco.dscontacorrente.resources;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farias.banco.dscontacorrente.dto.ContaCorrenteResponseDTO;
import com.farias.banco.dscontacorrente.service.ContaCorrenteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/contacorrente")
@RequiredArgsConstructor
public class ContaCorrenteResource {

	private final ContaCorrenteService service;

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
