package com.farias.banco.dspessoa.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.farias.banco.dspessoa.feignclients.ContaCorrenteFeignClients;
import com.farias.banco.dspessoa.model.Pessoa;
import com.farias.banco.dspessoa.repository.PessoaRepository;
import com.farias.banco.dspessoa.service.PessoaService;

/**
 * 
 * Os CPF usados foram obtidos aleatoriamente no https://www.4devs.com.br/gerador_de_cpf
 * portanto nenhum confere com o nome, pelo deveria ser assim.
 * 
 * @author farias
 *
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PessoaServiceTest {

	@Autowired
	private PessoaRepository repository;
	
	@MockBean
	private ContaCorrenteFeignClients contaCorrenteFeignClients;
	
	@Autowired
	private PessoaService service;
	
	@BeforeEach
	void setUp() throws Exception {
		repository.deleteAll();
	}
	
	@Test
	void deveValidarSeABaseEstaVazia_returnaTrue_quandoForVerdadeiro() {
		List<Pessoa> list = repository.findAll();
		assertTrue(list.isEmpty());
	}
	
	@Test
	void deveCriarCadastroDoTipoPF_returnaTrue_quandoForVerdadeiro() {
		Pessoa pessoaFisica = new Pessoa();
		pessoaFisica.setNome("Jośe Valadares");
		pessoaFisica.setCpfCnpj("47280738079");
		
		pessoaFisica = service.cadastrarPessoa(pessoaFisica);
		assertTrue(pessoaFisica.getTipo().equals("PF"));
	}
	
	@Test
	void deveCriarCadastroDoTipoPJ_returnaTrue_quandoForVerdadeiro() {
		
		Pessoa pessoaFisica = new Pessoa();
		pessoaFisica.setNome("Gomas & Dias - ME");
		pessoaFisica.setCpfCnpj("87320652000191");
		
		pessoaFisica = service.cadastrarPessoa(pessoaFisica);
		assertTrue(pessoaFisica.getTipo().equals("PJ"));
	}
	
	@Test
	void deveCriarCadastroComScoreEnte0e9_returnaTrue_quandoForVerdadeiro() {
		Pessoa pessoaFisica = new Pessoa();
		pessoaFisica.setNome("Asdrubaldo Ferro");
		pessoaFisica.setCpfCnpj("25661236085");
		
		pessoaFisica = service.cadastrarPessoa(pessoaFisica);
		assertTrue(pessoaFisica.getScore() > 0);
		assertTrue(pessoaFisica.getScore() < 9);
	}

}
