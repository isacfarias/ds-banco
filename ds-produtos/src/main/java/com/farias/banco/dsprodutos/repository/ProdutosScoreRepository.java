package com.farias.banco.dsprodutos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farias.banco.dsprodutos.model.ProdutoScore;

@Repository
public interface ProdutosScoreRepository extends JpaRepository<ProdutoScore, Integer> {

	List<ProdutoScore> findByScoreMinGreaterThanEqualAndScoreMaxLessThanEqual(Integer scoreMin, Integer scoreMax);
	
}
