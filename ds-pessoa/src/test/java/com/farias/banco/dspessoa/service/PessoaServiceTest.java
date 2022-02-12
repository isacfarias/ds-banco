package com.farias.banco.dspessoa.service;

import com.farias.banco.dspessoa.broker.outbound.PessoaBrokerOutbound;
import com.farias.banco.dspessoa.dto.PessoaResponseDTO;
import com.farias.banco.dspessoa.handler.exception.DataBaseException;
import com.farias.banco.dspessoa.model.Pessoa;
import com.farias.banco.dspessoa.repository.PessoaRepository;
import com.farias.banco.dspessoa.repository.specification.PessoaSpecification;
import com.farias.banco.dspessoa.utils.ScoreUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.farias.banco.dspessoa.creator.PessoaCreator.*;
import static com.farias.banco.dspessoa.creator.PessoaContaCorrenteCreator.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class PessoaServiceTest {
	@InjectMocks
	private PessoaService service;
	@Mock
	private PessoaRepository repository;
	@Mock
	private ScoreUtils scoreUtils;
	@Mock
	private PessoaBrokerOutbound brokerOutbound;

	private final Pessoa pessoaSaved = createSavedPF();
	private final PessoaResponseDTO pessoaResponseExpected = createPFtoDTO();
	private final Optional<String> filterNome = Optional.of(pessoaResponseExpected.getNome());
	private final Optional<String> filterTipo = Optional.of("PF");
	private final Optional<Integer> filterScore = Optional.of(4);
	private final PageRequest pageResquest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "nome"));

	@Test
	void findAllShouldReturnPessoaResponse() {
		when(repository.findAll(any(PessoaSpecification.class), any(PageRequest.class))).thenReturn(new PageImpl<>(List.of(pessoaSaved)));
		final var result = service.findAll(filterNome, filterTipo, filterScore, pageResquest).getContent();

		assertNotNull(result);
		final var content = result.get(0);
		assertEquals(pessoaResponseExpected.getNome(), content.getNome());
		assertEquals(pessoaResponseExpected.getCpfCnpj(), content.getCpfCnpj());
		assertEquals(pessoaResponseExpected.getTipo(), content.getTipo());
	}

	@Test
	void saveShouldReturnPessoaWhenCPFIsValid() {
		final var pessoaSave = createPF();
		when(repository.save(any())).thenReturn(createSavedPF());
		final var pessoaDTOResponse = service.save(createRequestPF());
		assertEquals("PF", pessoaDTOResponse.getTipo());
		verify(repository, times(1)).save(any());
	}

	@Test
	void saveShouldReturnPessoaWhenCNPJIsValid() {
		final var pessoaSave = createPJ();
		when(repository.save(any())).thenReturn(createSavedPJ());
		final var pessoaDTOResponse = service.save(createRequestPJ());
		assertEquals("PJ", pessoaDTOResponse.getTipo());
		verify(repository, times(1)).save(any());
	}

	@Test
	void updateShouldUpdatedStatusContaCorrenteWhenDataIsValid() {
		final var pessoaContaCorrenteUpdate = createPessoaContaCorrenteDTO();
		when(repository.findById(pessoaContaCorrenteUpdate.getPessoa())).thenReturn(Optional.of(createSavedPF()));
		when(repository.save(any())).thenReturn(createUpdatedContaCorrentePF());
		service.atualizarContaCorrente(pessoaContaCorrenteUpdate);
		verify(repository, times(1)).save(any());
	}

	@Test
	void updateShouldUpdatedStatusProdutoWhenDataIsValid() {
		final var pessoaContaCorrenteUpdate = createPessoaContaCorrenteDTO();
		when(repository.findById(pessoaContaCorrenteUpdate.getPessoa())).thenReturn(Optional.of(createSavedPF()));
		when(repository.save(any())).thenReturn(createUpdatedProdutosPF());
		service.atualizarContaCorrenteProdutos(pessoaContaCorrenteUpdate);
		verify(repository, times(1)).save(any());
	}

	@Test
	void updateContaCorrenteShouldExceptionWhenContaCorrenteIsNotExists() {
		final var pessoaContaCorrenteUpdate = createPessoaContaCorrenteDTO();
		when(repository.findById(pessoaContaCorrenteUpdate.getPessoa())).thenThrow(new DataBaseException(HttpStatus.NOT_FOUND, "Recurso não encontrado"));
		assertThrows(DataBaseException.class, () -> service.atualizarContaCorrente(pessoaContaCorrenteUpdate));
	}

	@Test
	void updateProdutoShouldExceptionWhenContaCorrenteIsNotExists() {
		final var pessoaContaCorrenteUpdate = createPessoaContaCorrenteDTO();
		when(repository.findById(pessoaContaCorrenteUpdate.getPessoa())).thenThrow(new DataBaseException(HttpStatus.NOT_FOUND, "Recurso não encontrado"));
		assertEquals(HttpStatus.NOT_FOUND ,assertThrows(DataBaseException.class, () -> service.atualizarContaCorrenteProdutos(pessoaContaCorrenteUpdate)).getStatus());
	}



}
