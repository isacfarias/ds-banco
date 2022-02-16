package com.farias.banco.dscontacorrente.service;

import static com.farias.banco.dscontacorrente.creators.PessoaCreator.createPJ;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.farias.banco.dscontacorrente.modules.config.app.AppConfig;
import com.farias.banco.dscontacorrente.modules.integration.broker.supplier.ContaCorrenteMessageSupplier;
import com.farias.banco.dscontacorrente.modules.integration.feign.ContaCorrenteProdutosFeignClients;
import com.farias.banco.dscontacorrente.modules.repository.ContaCorrenteRepository;
import com.farias.banco.dscontacorrente.utils.ContaCorrenteNumeroUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;

import com.farias.banco.dscontacorrente.enums.ContaTipo;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static com.farias.banco.dscontacorrente.creators.PessoaCreator.createPF;
import static com.farias.banco.dscontacorrente.creators.ContaCorrenteCreator.createContaCorrentePFSaved;
import static com.farias.banco.dscontacorrente.creators.ContaCorrenteCreator.createContaCorrentePJSaved;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class ContaCorrenteServiceTest {
	
	@InjectMocks
	private ContaCorrenteService contaCorrenteService;
	@Mock
	private ContaCorrenteRepository repository;
	@Mock
	private ContaCorrenteProdutosFeignClients contaCorrenteProdutosFeignClients;
	@Mock
	private ContaCorrenteNumeroUtils contaNumero;
	@Mock
	private ContaCorrenteMessageSupplier supplier;
	@Mock
	private AppConfig config;

	@BeforeEach
	void setUp() throws Exception {
		final var agencia = new AppConfig.Agencia();
		agencia.setNumero(5478);
		when(config.getAgencia()).thenReturn(agencia);
	}

	@Test
	void saveShouldReturnContaCorrenteTypeC_WhenDataIsValid() {
		var pessoaRequest = createPF();
		when(contaNumero.numeroConta()).thenReturn(457823);
		when(repository.findByNumero(any())).thenReturn(List.of());
		when(repository.save(any())).thenReturn(createContaCorrentePFSaved());
		final var contaCorrenteSaved = contaCorrenteService.cadastrarContaCorrente(pessoaRequest);
		assertTrue(contaCorrenteSaved.getTipo().equals(ContaTipo.C));
	}

	@Test
	void saveShouldReturnContaCorrenteTypeE_WhenDataIsValid() {
		var pessoaRequest = createPJ();
		when(contaNumero.numeroConta()).thenReturn(457823);
		when(repository.findByNumero(any())).thenReturn(List.of());
		when(repository.save(any())).thenReturn(createContaCorrentePJSaved());
		final var contaCorrenteSaved = contaCorrenteService.cadastrarContaCorrente(pessoaRequest);
		assertTrue(contaCorrenteSaved.getTipo().equals(ContaTipo.E));
	}

}
