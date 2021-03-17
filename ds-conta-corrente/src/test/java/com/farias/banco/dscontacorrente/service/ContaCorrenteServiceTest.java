package com.farias.banco.dscontacorrente.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.farias.banco.dscontacorrente.enums.ContaTipo;
import com.farias.banco.dscontacorrente.model.ContaCorrente;
import com.farias.banco.dscontacorrente.model.Pessoa;


@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ContaCorrenteServiceTest {
	
	@Autowired
	private ContaCorrenteService contaCorrenteService;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Order(1)
	void deveCriarContaCorrenteDoTipoC_retornaTrue_quandoResultadoForVerdadeiro() {
		Pessoa pessoa = new Pessoa(1L, "Jorge paulo", "09152280098", 2, "PF");
		ContaCorrente contaCorrebnte = contaCorrenteService.cadastrarContaCorrente(pessoa);
		assertTrue(contaCorrebnte.getTipo().equals(ContaTipo.C));
	}
	
	@Test
	@Order(2)
	void deveCriarContaCorrenteDoTipoE_retornaTrue_quandoResultadoForVerdadeiro() {
		Pessoa pessoa = new Pessoa(1L, "Gomes & Dias - ME", "80107417000197", 7, "PJ");
		ContaCorrente contaCorrebnte = contaCorrenteService.cadastrarContaCorrente(pessoa);
		assertTrue(contaCorrebnte.getTipo().equals(ContaTipo.E));
	}

}
