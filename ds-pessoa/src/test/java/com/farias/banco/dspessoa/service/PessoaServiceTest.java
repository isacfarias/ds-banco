package com.farias.banco.dspessoa.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.farias.banco.dspessoa.dto.PessoaDTORequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.farias.banco.dspessoa.feignclients.ContaCorrenteFeignClients;
import com.farias.banco.dspessoa.model.Pessoa;
import com.farias.banco.dspessoa.repository.PessoaRepository;

/**
 *
 * Os CPF usados foram obtidos aleatoriamente no https://www.4devs.com.br/gerador_de_cpf
 * portanto nenhum confere com o nome, pelo deveria ser assim.
 *
 * @author farias
 *
 */

@ActiveProfiles("test")
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
		final var pessoaFisica = PessoaDTORequest.builder()
				.nome("Jośe Valadares")
				.cpfCnpj("47280738079")
				.build();

		final var pessoaDTOResponse = service.cadastrarPessoa(pessoaFisica);
		assertTrue(pessoaFisica.getTipo().equals("PF"));
	}

	@Test
	void deveCriarCadastroDoTipoPJ_returnaTrue_quandoForVerdadeiro() {
		final var pessoaFisica = PessoaDTORequest.builder()
				.nome("Gomas & Dias - ME")
				.cpfCnpj("87320652000191")
				.build();

		var pessoaDTOResponse = service.cadastrarPessoa(pessoaFisica);
		assertTrue(pessoaFisica.getTipo().equals("PJ"));
	}

	@Test
	void deveCriarCadastroComScoreEnte0e9_returnaTrue_quandoForVerdadeiro() {
		final var pessoaFisica = PessoaDTORequest.builder()
				.nome("Asdrubaldo Ferro")
				.cpfCnpj("25661236085")
				.build();

		final var pessoaDTOResponse = service.cadastrarPessoa(pessoaFisica);
		assertTrue(pessoaFisica.getScore() > 0);
		assertTrue(pessoaFisica.getScore() < 9);
	}

}
