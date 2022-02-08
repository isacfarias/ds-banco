package com.farias.banco.dscontacorrenteprodutos.service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import com.farias.banco.dscontacorrenteprodutos.broker.outbound.ProdutosContaCorrenteBrokerOutbound;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.farias.banco.dscontacorrenteprodutos.dto.ContaCorrenteProdutoDTO;
import com.farias.banco.dscontacorrenteprodutos.dto.PessoaContaCorrenteDTO;
import com.farias.banco.dscontacorrenteprodutos.feignclients.ProdutosFeignClient;
import com.farias.banco.dscontacorrenteprodutos.model.ContaCorrenteProdutos;
import com.farias.banco.dscontacorrenteprodutos.model.ProdutoTipo;
import com.farias.banco.dscontacorrenteprodutos.repository.ContaCorrenteProdutosRepository;
import com.farias.banco.dscontacorrenteprodutos.repository.specification.ContaCorrenteProdutosSpecification;

import static com.farias.banco.dscontacorrenteprodutos.contants.ContaCorrenteConstants.ATIVO;
import static com.farias.banco.dscontacorrenteprodutos.contants.ContaCorrenteConstants.INATIVO;
import static com.farias.banco.dscontacorrenteprodutos.contants.ContaCorrenteConstants.PROD_CARTAO_CREDITO;

@Service
@RequiredArgsConstructor
public class ContaCorrenteProdutosService {

	private final Logger LOG = LoggerFactory.getLogger(ContaCorrenteProdutosService.class);

	private final ContaCorrenteProdutosRepository repository;
	private final ProdutosFeignClient produtosScoreFeignClient;
	private final ProdutosContaCorrenteBrokerOutbound outbound;

	public void vincularProdutosContaCorrente(PessoaContaCorrenteDTO pessoaContaCorrente) {
		try {
			Objects.requireNonNull(produtosScoreFeignClient.produtosPorScore(pessoaContaCorrente.getScore()).getBody())
					.stream()
			.filter( produto -> !(produto.getProduto().equals(PROD_CARTAO_CREDITO)
					&& produto.getValor().compareTo(new BigDecimal("0.0")) <= 0 ))
			.forEach(produto -> repository.save(ContaCorrenteProdutos.builder()
					.ativo(produto.getValor().compareTo(new BigDecimal("0.0")) > 0 ? ATIVO : INATIVO)
					.contaCorrente(pessoaContaCorrente.getContaCorrente())
					.produtoTipo(produto.getProduto())
					.valor(produto.getValor())
					.build()
			));

			outbound.publishProdutosContaCorrenteCreated(pessoaContaCorrente);

		} catch (Exception e) {
			LOG.error("O serviço [ds-produtos] de produtos esta Off.", e.getMessage());
		}
	}

	public Page<ContaCorrenteProdutoDTO> findAll(final Optional<Long> id, final Optional<Long> contaCorrente, final Optional<Integer> ativo, final Optional<Long> produtoTipo, PageRequest pageable) {
		return repository.findAll(ContaCorrenteProdutosSpecification.builder()
				.id(id)
				.ativo(ativo)
				.contaCorrente(contaCorrente)
				.produtoTipo(produtoTipo)
				.build(), pageable).map(this::buildDTO);
	}

	public ContaCorrenteProdutoDTO buildDTO(ContaCorrenteProdutos contaCorrenteProdutos) {
		ResponseEntity<ProdutoTipo> produtoTipo = null;
		try {
			produtoTipo = produtosScoreFeignClient.produto(contaCorrenteProdutos.getProdutoTipo());
		} catch (Exception e) {
			LOG.error("O serviço [ds-produtos] de produtos esta Off.", e.getMessage());
		}
		return ContaCorrenteProdutoDTO.builder()
				.produto(Optional.of(produtoTipo.getBody()).map(ProdutoTipo::getDescricao).orElse("N/A"))
				.limite(contaCorrenteProdutos.getValor())
				.build();
	}

}
