package com.farias.banco.dscontacorrenteprodutos.service;

import static com.farias.banco.dscontacorrenteprodutos.creators.ContaCorrenteProdutosCreator.createContaCorrenteProdutos;
import static com.farias.banco.dscontacorrenteprodutos.creators.PessoaContaCorrenteCreator.createContaCorrente;
import static com.farias.banco.dscontacorrenteprodutos.creators.ProdutoTipoCreator.createProdutosTipoDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.farias.banco.dscontacorrenteprodutos.creators.PessoaContaCorrenteCreator;
import com.farias.banco.dscontacorrenteprodutos.dto.ProdutosDTO;
import com.farias.banco.dscontacorrenteprodutos.modules.integration.broker.supplier.ProdutosContaCorrenteMessageSupplier;
import com.farias.banco.dscontacorrenteprodutos.modules.integration.feign.ProdutosFeignClient;
import com.farias.banco.dscontacorrenteprodutos.modules.integration.feign.impl.PageResourcesImpl;
import com.farias.banco.dscontacorrenteprodutos.modules.repository.ContaCorrenteProdutosRepository;
import com.farias.banco.dscontacorrenteprodutos.modules.repository.specification.ContaCorrenteProdutosSpecification;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class ContaCorrenteProdutosServiceTest {

	@InjectMocks
	private ContaCorrenteProdutosService service;
	@Mock
	private ContaCorrenteProdutosRepository repository;
	@Mock
	private ProdutosFeignClient feing;
	@Mock
	private ProdutosContaCorrenteMessageSupplier outbound;

	@BeforeEach
	void setUp() throws Exception {
		repository.deleteAll();
	}

	@Test
	void vincularProdutosContaCorrenteShouldWhenDataisValid() {
		
		final var produtos = List.of(new ProdutosDTO(1, new BigDecimal("1000.0")), new ProdutosDTO(2, new BigDecimal("200.0")));
		when(feing.produtosPorScore(createContaCorrente().getScore())).thenReturn(ResponseEntity.ok(produtos));
		when(repository.save(any())).thenReturn(createContaCorrenteProdutos());
		service.vincularProdutosContaCorrente(PessoaContaCorrenteCreator.createContaCorrente());
		
		verify(repository, times(2)).save(any());
	}
	
	@Test
	void findAllShouldPagededContaCorrenteProdutoDTO() {
		Optional<Long> id = null;
		Optional<Long> contaCorrente = null;
		Optional<Integer> ativo = null;
		Optional<Long> produtoTipo = null;
		PageRequest pageResquest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "nome"));
		when(feing.produto(createContaCorrenteProdutos().getProdutoTipo(), PageRequest.of(0, 10))).thenReturn(new PageResourcesImpl<>(List.of(createProdutosTipoDTO())));
		when(repository.findAll(any(ContaCorrenteProdutosSpecification.class), any(PageRequest.class))).thenReturn(new PageImpl<>(List.of(createContaCorrenteProdutos())));

		final var result = service.findAll(id, contaCorrente, ativo, produtoTipo, pageResquest).getContent();

		assertNotNull(result);
		assertNotNull(result.get(0));
		final var content = result.get(0).get(0);
		assertEquals(createProdutosTipoDTO().getDescricao(), content.getProduto());
	}

	

}
