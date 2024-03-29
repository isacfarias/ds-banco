package com.farias.banco.contacorrenteprodutos.modules.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.farias.banco.contacorrenteprodutos.modules.model.ContaCorrenteProdutos;

@Repository
public interface ContaCorrenteProdutosRepository extends JpaRepository<ContaCorrenteProdutos, Long>, JpaSpecificationExecutor<ContaCorrenteProdutos> {
	
	List<ContaCorrenteProdutos> findByContaCorrente(Long contaCorrente);

}
