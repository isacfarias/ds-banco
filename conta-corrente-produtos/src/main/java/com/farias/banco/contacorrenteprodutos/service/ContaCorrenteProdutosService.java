package com.farias.banco.contacorrenteprodutos.service;

import static com.farias.banco.contacorrenteprodutos.contants.ContaCorrenteConstants.PROD_CARTAO_CREDITO;
import static com.farias.banco.contacorrenteprodutos.contants.MapperConstants.contaCorrenteMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.farias.banco.contacorrenteprodutos.modules.integration.broker.supplier.ProdutosContaCorrenteMessageSupplier;
import com.farias.banco.contacorrenteprodutos.dto.ContaCorrenteProdutoDTO;
import com.farias.banco.contacorrenteprodutos.dto.PessoaContaCorrenteDTO;
import com.farias.banco.contacorrenteprodutos.modules.integration.feign.ProdutosFeignClient;
import com.farias.banco.contacorrenteprodutos.modules.model.ContaCorrenteProdutos;
import com.farias.banco.contacorrenteprodutos.modules.repository.ContaCorrenteProdutosRepository;
import com.farias.banco.contacorrenteprodutos.modules.repository.specification.ContaCorrenteProdutosSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContaCorrenteProdutosService {

	private final Logger LOG = LoggerFactory.getLogger(ContaCorrenteProdutosService.class);

	private final ContaCorrenteProdutosRepository repository;
	private final ProdutosFeignClient produtosScoreFeignClient;
	private final ProdutosContaCorrenteMessageSupplier outbound;

	public void vincularProdutosContaCorrente(PessoaContaCorrenteDTO pessoaContaCorrente) {
		try {
			Objects.requireNonNull(produtosScoreFeignClient.produtosPorScore(pessoaContaCorrente.getScore()).getBody())
			.stream()
			.filter( produto -> !(produto.getProduto().equals(PROD_CARTAO_CREDITO)
					&& produto.getValor().compareTo(new BigDecimal("0.0")) <= 0 ))
			.forEach(produto -> 
			         repository.save(contaCorrenteMapper.buildContaCorrenteProdutos(produto)
					.withContaCorrente(pessoaContaCorrente.getContaCorrente())
					));
			
			outbound.publishProdutosContaCorrenteProcessed(pessoaContaCorrente);

		} catch (Exception e) {
			LOG.error("O serviço [ds-produtos] de produtos esta Off.", e.getMessage());
		}
	}

	public Page<List<ContaCorrenteProdutoDTO>> findAll(final Optional<Long> id, final Optional<Long> contaCorrente, final Optional<Integer> ativo, final Optional<Long> produtoTipo, PageRequest pageable) {
		return repository.findAll(ContaCorrenteProdutosSpecification.builder()
				.id(id)
				.ativo(ativo)
				.contaCorrente(contaCorrente)
				.produtoTipo(produtoTipo)
				.build(), pageable).map(this::buildDTO);
	}

	private List<ContaCorrenteProdutoDTO> buildDTO(ContaCorrenteProdutos contaCorrenteProdutos) {
		List<ContaCorrenteProdutoDTO> produtos = null;
		try {
			produtos = produtosScoreFeignClient.produto(contaCorrenteProdutos.getProdutoTipo(), PageRequest.of(0, 10)).getContent()
					.stream()
					.filter(Objects:: nonNull)
					.map(c -> contaCorrenteMapper.buildContaCorrenteProdutosDTO(c)
							.withLimite(contaCorrenteProdutos.getValor()) )
					.collect(Collectors.toList());


		} catch (Exception e) {
			LOG.error("O serviço [ds-produtos] de produtos esta Off.", e.getMessage());
		}

		return produtos; 
	}

}
