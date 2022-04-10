package com.farias.banco.contacorrente.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.farias.banco.contacorrente.dto.ContaCorrenteProdutoDTO;
import com.farias.banco.contacorrente.modules.integration.feign.ContaCorrenteProdutosFeignClients;
import com.farias.banco.contacorrente.modules.model.ContaCorrente;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j()
@Service
@RequiredArgsConstructor
public class ContaCorrenteProdutosService {
	
	
	private final ContaCorrenteProdutosFeignClients contaCorrenteProdutosFeignClients;
	private final Map<Long, List<ContaCorrenteProdutoDTO>> produtosCache;
	
	
	@CircuitBreaker(name = "findProductAccount", fallbackMethod = "findProductAccountCache")
	public List<ContaCorrenteProdutoDTO> findProductAccount(ContaCorrente contaCorrente) {
		
		List<ContaCorrenteProdutoDTO> produtos;
		Long key = contaCorrente.getId();
		
		try {
			produtos = contaCorrenteProdutosFeignClients.contaCorrenteProdutos(key, PageRequest.of(0, 10)).getContent()
					  .stream()
					  .flatMap(Collection<ContaCorrenteProdutoDTO>::stream)
					  .collect(Collectors.toList());
		} catch (Exception e) {
			log.error("O serviço [ds-conta-corrente-produtos] listar produtos vinculados a conta corrente não está respondendo.", e.getMessage());
			throw e;
		}
		
		produtosCache.put(key, produtos);
		
		return produtos;
	}
	
	@SuppressWarnings("unused")
	private List<ContaCorrenteProdutoDTO> findProductAccountCache(ContaCorrente contaCorrente, Throwable e) {
		return produtosCache.getOrDefault(contaCorrente.getId(), List.of());
	}

}
