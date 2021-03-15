package com.farias.banco.dscontacorrenteprodutos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farias.banco.dscontacorrenteprodutos.dto.PessoaContaCorrenteDTO;
import com.farias.banco.dscontacorrenteprodutos.dto.ProdutosDTO;
import com.farias.banco.dscontacorrenteprodutos.feignclients.ProdutosScoreFeignClient;
import com.farias.banco.dscontacorrenteprodutos.model.ContaCorrenteProdutos;
import com.farias.banco.dscontacorrenteprodutos.repository.ContaCorrenteProdutosRepository;

@Service
public class ContaCorrenteProdutosService {

	@Autowired
	private ContaCorrenteProdutosRepository repository;

	@Autowired
	private ProdutosScoreFeignClient produtosScoreFeignClient; 

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
}
