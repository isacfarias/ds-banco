package com.farias.banco.dscontacorrenteprodutos.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.farias.banco.dscontacorrenteprodutos.contants.ContaCorrenteConstants;
import com.farias.banco.dscontacorrenteprodutos.dto.PessoaContaCorrenteDTO;
import com.farias.banco.dscontacorrenteprodutos.dto.ProdutosDTO;
import com.farias.banco.dscontacorrenteprodutos.model.ContaCorrenteProdutos;
import com.farias.banco.dscontacorrenteprodutos.repository.ContaCorrenteProdutosRepository;



@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ContaCorrenteProdutosServiceTest {

	@Autowired
	private ContaCorrenteProdutosRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		repository.deleteAll();
	}

	@Test
	void deveVincularProdutoSChequeEspecialECartaoAContaCorrente() {
		PessoaContaCorrenteDTO contaCorrente = PessoaContaCorrenteDTO.builder()
				.contaCorrente(1l)
				.pessoa(2l)
				.score(3)
				.build();

		List<ProdutosDTO> produtos = Arrays.asList(new ProdutosDTO(1, new BigDecimal("1000.0")), new ProdutosDTO(2, new BigDecimal("200.0")));

		ContaCorrenteProdutos contaCorrenteProdutos;
		for (ProdutosDTO produto : produtos) {
			if (produto.getProduto().equals(ContaCorrenteConstants.PROD_CARTAO_CREDITO)
					&& produto.getValor().compareTo(new BigDecimal("0.0")) <= 0 ) continue;

			contaCorrenteProdutos = ContaCorrenteProdutos.builder()
					.contaCorrente(contaCorrente.getContaCorrente())
					.produtoTipo(produto.getProduto())
					.ativo((produto.getValor().compareTo(new BigDecimal("0.0")) > 0 ? 1 : 0))
					.valor(produto.getValor())
					.build();
			repository.saveAndFlush(contaCorrenteProdutos);
		}

		List<ContaCorrenteProdutos> produtosVinculados = repository.findByContaCorrente(1l);
		assertTrue(produtosVinculados.size() == 2);

	}
	
	@Test
	void deveVincularProdutoSChequeEspecialAContaCorrente() {
		PessoaContaCorrenteDTO contaCorrente = PessoaContaCorrenteDTO.builder()
				.contaCorrente(2l)
				.pessoa(1l)
				.score(0)
				.build();

		List<ProdutosDTO> produtos = Arrays.asList(new ProdutosDTO(1, new BigDecimal("0.0")), new ProdutosDTO(2, new BigDecimal("0.0")));

		ContaCorrenteProdutos contaCorrenteProdutos;
		for (ProdutosDTO produto : produtos) {
			if (produto.getProduto().equals(ContaCorrenteConstants.PROD_CARTAO_CREDITO)
					&& produto.getValor().compareTo(new BigDecimal("0.0")) <= 0 ) continue;

			contaCorrenteProdutos = ContaCorrenteProdutos.builder()
					.contaCorrente(contaCorrente.getContaCorrente())
					.produtoTipo(produto.getProduto())
					.ativo(( produto.getValor().compareTo(new BigDecimal("0.0")) > 0 ? 1: 0 ))
					.valor(produto.getValor())
					.build();
			repository.saveAndFlush(contaCorrenteProdutos);
		}

		List<ContaCorrenteProdutos> produtosVinculados = repository.findByContaCorrente(2l);
		assertTrue(produtosVinculados.size() == 1);

	}

}
