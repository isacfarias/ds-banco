package com.farias.banco.dsprodutos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farias.banco.dsprodutos.model.ProdutoFaixa;

@Repository
public interface ProdutosFaixaRepository extends JpaRepository<ProdutoFaixa, Integer> {

	List<ProdutoFaixa> findByScoreMinGreaterThanEqualAndScoreMaxLessThanEqual(Integer scoreMin, Integer scoreMax);
	
}
