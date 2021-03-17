package com.farias.banco.dscontacorrenteprodutos.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farias.banco.dscontacorrenteprodutos.contants.ContaCorrenteConstants;
import com.farias.banco.dscontacorrenteprodutos.dto.ContaCorrenteProdutoDTO;
import com.farias.banco.dscontacorrenteprodutos.dto.PessoaContaCorrenteDTO;
import com.farias.banco.dscontacorrenteprodutos.dto.ProdutosDTO;
import com.farias.banco.dscontacorrenteprodutos.feignclients.ProdutosFeignClient;
import com.farias.banco.dscontacorrenteprodutos.model.ContaCorrenteProdutos;
import com.farias.banco.dscontacorrenteprodutos.repository.ContaCorrenteProdutosRepository;

@Service
public class ContaCorrenteProdutosService {

	private final Logger LOG = LoggerFactory.getLogger(ContaCorrenteProdutosService.class);

	@Autowired
	private ContaCorrenteProdutosRepository repository;

	@Autowired
	private ProdutosFeignClient produtosScoreFeignClient;


	public void vincularProdutosContaCorrente(PessoaContaCorrenteDTO pessoaContaCorrente) {
		List<ProdutosDTO> produtos = null;
		
		try {
			produtos = produtosScoreFeignClient.produtosPorScore(pessoaContaCorrente.getScore()).getBody();
		} catch (Exception e) {
			LOG.error("O serviço [ds-produtos] de produtos esta Off.", e.getMessage());
		}

		ContaCorrenteProdutos contaCorrenteProdutos;
		for (ProdutosDTO produto : produtos) {

			if (produto.getProduto().equals(ContaCorrenteConstants.PROD_CARTAO_CREDITO)
					&& produto.getValor().compareTo(new BigDecimal(0.0)) <= 0 ) continue;
			
			contaCorrenteProdutos = new ContaCorrenteProdutos();
			contaCorrenteProdutos.setContaCorrente(pessoaContaCorrente.getContaCorrente());
			contaCorrenteProdutos.setProdutoTipo(produto.getProduto());
			contaCorrenteProdutos.setAtivo( ( produto.getValor().compareTo(new BigDecimal(0.0)) == 1 ? 1: 0 ) );
			contaCorrenteProdutos.setValor(produto.getValor());

			repository.save(contaCorrenteProdutos);
		}
	}

	public List<ContaCorrenteProdutoDTO> searchProdutosContaCorrente(Long contaCorrenteId) {
		List<ContaCorrenteProdutoDTO> contaCorrenteprodutos = null;
		List<ContaCorrenteProdutos> produtos = repository.findByContaCorrente(contaCorrenteId);
		try {
			contaCorrenteprodutos = produtos.stream()
					.map(contaCorrenteProduto -> new ContaCorrenteProdutoDTO(produtosScoreFeignClient.produto(contaCorrenteProduto.getProdutoTipo()).getBody().getDescricao(), contaCorrenteProduto.getValor()))
					.collect(Collectors.toList());
		} catch (Exception e) {
			LOG.error("O serviço [ds-produtos] de produtos esta Off.", e.getMessage());
		}

		return contaCorrenteprodutos;
	}
}
