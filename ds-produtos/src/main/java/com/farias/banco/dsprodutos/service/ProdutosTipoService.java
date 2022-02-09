package com.farias.banco.dsprodutos.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.farias.banco.dsprodutos.dto.ProdutosTipoDTO;
import com.farias.banco.dsprodutos.model.ProdutoTipo;
import com.farias.banco.dsprodutos.repository.ProdutosTipoRepository;
import com.farias.banco.dsprodutos.repository.specification.ProdutoTipoSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutosTipoService {

	private final ProdutosTipoRepository repository;

	public Page<ProdutosTipoDTO> findAll(final Optional<String> descricao, final Optional<Long> produtoTipoId, PageRequest pageable) {
		return repository.findAll(ProdutoTipoSpecification.builder()
				.descricao(descricao)
				.produtoTipoId(produtoTipoId)
				.build(), pageable).map(this::buildDTO);
	}


	public ProdutosTipoDTO save(ProdutoTipo produtoTipo) {
		return buildDTO(repository.save(produtoTipo));
	}
	
	private ProdutosTipoDTO buildDTO(ProdutoTipo p) {
		return ProdutosTipoDTO.builder()
				.id(p.getId())
				.descricao(p.getDescricao())
				.build();
	}

}
