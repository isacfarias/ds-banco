package com.farias.banco.dsprodutos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.farias.banco.dsprodutos.dto.ProdutoFaixaDTO;
import com.farias.banco.dsprodutos.dto.ProdutoFaixaDTORequest;
import com.farias.banco.dsprodutos.dto.ProdutoValorDTO;
import com.farias.banco.dsprodutos.dto.ProdutosTipoDTO;
import com.farias.banco.dsprodutos.model.ProdutoFaixa;
import com.farias.banco.dsprodutos.model.ProdutoTipo;
import com.farias.banco.dsprodutos.repository.ProdutosFaixaRepository;
import com.farias.banco.dsprodutos.repository.specification.ProdutoFaixaSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutosFaixaService {

	private final ProdutosFaixaRepository repository;

	public Page<ProdutoFaixaDTO> findAll(final Optional<Long> id, final Optional<Long> produtoTipo,	
			final Optional<Long> scoreMin, final Optional<Long> scoreMax,
			final Optional<Long> valor, PageRequest pageable) {

		return repository.findAll(ProdutoFaixaSpecification.builder()
				.id(id)
				.produtoTipo(produtoTipo)
				.scoreMax(scoreMax)
				.scoreMin(scoreMin)
				.valor(valor)
				.build(), pageable).map(this::buildDTO);
	}

	public List<ProdutoValorDTO> findByScore(Integer score) {
		return repository.findByScoreFaixa(score)
				.stream()
				.map(this::buildProdutoValorDTO)
				.collect(Collectors.toList());
	}

	public ProdutoFaixaDTO save(ProdutoFaixaDTORequest produtoFaixa) {
		return buildDTO(repository.save(ProdutoFaixa.builder()
				.produtoTipo(ProdutoTipo.builder()
						.id(produtoFaixa.getProdutoTipoId())
						.build())
				.scoreMax(produtoFaixa.getScoreMax())
				.scoreMin(produtoFaixa.getScoreMin())
				.valor(produtoFaixa.getValor())
				.build()));
	}

	private ProdutoFaixaDTO buildDTO(ProdutoFaixa p) {
		return ProdutoFaixaDTO.builder()
				.id(p.getId())
				.produtoTipo(buildProdutoTipoDTO(p))
				.scoreMax(p.getScoreMax())
				.scoreMin(p.getScoreMin())
				.valor(p.getValor())
				.build();
	}

	private ProdutoValorDTO buildProdutoValorDTO(ProdutoFaixa p) {
		return ProdutoValorDTO.builder()
				.produto(p.getProdutoTipo().getId())
				.valor(p.getValor())
				.build();
	}

	private ProdutosTipoDTO buildProdutoTipoDTO(ProdutoFaixa p) {
		return ProdutosTipoDTO.builder()
				.id(p.getProdutoTipo().getId())
				.descricao(p.getProdutoTipo().getDescricao())
				.build();
	}

}
