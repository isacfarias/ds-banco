package com.farias.banco.contacorrenteprodutos.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.farias.banco.contacorrenteprodutos.dto.PessoaContaCorrenteDTO;
import com.farias.banco.contacorrenteprodutos.dto.ProdutosDTO;
import com.farias.banco.contacorrenteprodutos.dto.ProdutosTipoDTO;
import com.farias.banco.contacorrenteprodutos.modules.integration.feign.ProdutosFeignClient;
import com.farias.banco.contacorrenteprodutos.modules.model.ContaCorrenteProdutos;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutosService {

	private final ProdutosFeignClient produtosScoreFeignClient;
	private final Map<Integer, List<ProdutosDTO>> produtosCache;
	private final Map<Integer, List<ProdutosTipoDTO>> produtosTipoCache;

	@CircuitBreaker(name = "findProductScore", fallbackMethod = "findProductScoreCache")
	public List<ProdutosDTO> findProductScore(PessoaContaCorrenteDTO pessoaContaCorrente) {
		final var score = pessoaContaCorrente.getScore();
		final var produtos = produtosScoreFeignClient.produtosPorScore(score).getBody();
		produtosCache.put(score, produtos);
		return produtos;
	}

	@SuppressWarnings("unused")
	private List<ProdutosDTO> findProductScoreCache(PessoaContaCorrenteDTO pessoaContaCorrente, Throwable e) {
		return produtosCache.getOrDefault(pessoaContaCorrente.getScore(), List.of());
	}


	@CircuitBreaker(name = "findProductType", fallbackMethod = "findProductTypeCache")
	public List<ProdutosTipoDTO> findProductType(ContaCorrenteProdutos contaCorrenteProdutos) {
		final var produtoTipo = contaCorrenteProdutos.getProdutoTipo();
		final var produtos = produtosScoreFeignClient.produto(produtoTipo, PageRequest.of(0, 10)).getContent();
		produtosTipoCache.put(produtoTipo, produtos);
		return produtos;
	}

	@SuppressWarnings("unused")
	private List<ProdutosTipoDTO> findProductTypeCache(ContaCorrenteProdutos contaCorrenteProdutos, Throwable e) {
		return produtosTipoCache.getOrDefault(contaCorrenteProdutos.getProdutoTipo(), List.of());
	}
}
