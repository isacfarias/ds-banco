package com.farias.banco.dscontacorrenteprodutos.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.farias.banco.dscontacorrenteprodutos.dto.ContaCorrenteProdutoDTO;
import com.farias.banco.dscontacorrenteprodutos.dto.PessoaContaCorrenteDTO;
import com.farias.banco.dscontacorrenteprodutos.dto.ProdutosDTO;
import com.farias.banco.dscontacorrenteprodutos.model.ContaCorrenteProdutos;
import com.farias.banco.dscontacorrenteprodutos.repository.ContaCorrenteProdutosRepository;



@TestMethodOrder(OrderAnnotation.class)
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
	void deveVincularProdutoAContaCorrente() {
		PessoaContaCorrenteDTO contaCorrente = new PessoaContaCorrenteDTO();
		contaCorrente.setContaCorrente(1l);
		contaCorrente.setPessoa(2l);
		contaCorrente.setScore(3);
		
		List<ProdutosDTO> produtos = Arrays.asList(new ProdutosDTO(1, new BigDecimal(1000.0)), new ProdutosDTO(2, new BigDecimal(200.0)));
		
		ContaCorrenteProdutos contaCorrenteProdutos;
		for (ProdutosDTO produto : produtos) {
			
			contaCorrenteProdutos = new ContaCorrenteProdutos();
			contaCorrenteProdutos.setContaCorrente(contaCorrente.getContaCorrente());
			contaCorrenteProdutos.setProdutoTipo(produto.getProduto());
			contaCorrenteProdutos.setValor(produto.getValor());

			repository.saveAndFlush(contaCorrenteProdutos);
		}
		
		List<ContaCorrenteProdutos> produtosVinculados = repository.findByContaCorrente(1l);
		assertTrue(produtosVinculados.size() == 2);
		
	}

}
