package com.farias.banco.dscontacorrenteprodutos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farias.banco.dscontacorrenteprodutos.dto.ContaCorrenteProdutoDTO;
import com.farias.banco.dscontacorrenteprodutos.dto.PessoaContaCorrenteDTO;
import com.farias.banco.dscontacorrenteprodutos.dto.ProdutosDTO;
import com.farias.banco.dscontacorrenteprodutos.feignclients.ProdutosFeignClient;
import com.farias.banco.dscontacorrenteprodutos.model.ContaCorrenteProdutos;
import com.farias.banco.dscontacorrenteprodutos.repository.ContaCorrenteProdutosRepository;

@Service
public class ContaCorrenteProdutosService {

	@Autowired
	private ContaCorrenteProdutosRepository repository;

	@Autowired
	private ProdutosFeignClient produtosScoreFeignClient;
	

	public void vincularProdutosContaCorrente(PessoaContaCorrenteDTO pessoaContaCorrente) {

		List<ProdutosDTO> produtos = produtosScoreFeignClient.produtosPorScore(pessoaContaCorrente.getScore()).getBody();

		if (produtos.isEmpty()) return;

		ContaCorrenteProdutos contaCorrenteProdutos;
		for (ProdutosDTO produto : produtos) {
			
			contaCorrenteProdutos = new ContaCorrenteProdutos();
			contaCorrenteProdutos.setContaCorrente(pessoaContaCorrente.getContaCorrente());
			contaCorrenteProdutos.setProdutoTipo(produto.getProduto());
			contaCorrenteProdutos.setValor(produto.getValor());

			repository.save(contaCorrenteProdutos);
		}
	}

	public List<ContaCorrenteProdutoDTO> searchProdutosContaCorrente(Long contaCorrenteId) {
		
		List<ContaCorrenteProdutos> produtos = repository.findByContaCorrente(contaCorrenteId);
		
		List<ContaCorrenteProdutoDTO> contaCorrenteprodutos = produtos
				 .stream()
				 .map(contaCorrenteProduto -> new ContaCorrenteProdutoDTO(produtosScoreFeignClient.produto(contaCorrenteProduto.getProdutoTipo()).getBody().getDescricao(), contaCorrenteProduto.getValor()))
				 .collect(Collectors.toList());
		
		return contaCorrenteprodutos;
	}
}
