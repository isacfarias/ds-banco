package com.farias.banco.produtos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farias.banco.produtos.model.ProdutoFaixa;

@Repository
public interface ProdutosFaixaRepository extends JpaRepository<ProdutoFaixa, Long>, JpaSpecificationExecutor<ProdutoFaixa> {

	@Query(value = "select * from produtofaixa where :score between prof_scoremin and prof_scoremax", nativeQuery = true)
	List<ProdutoFaixa> findByScoreFaixa(@Param("score") Integer score);
	
}
