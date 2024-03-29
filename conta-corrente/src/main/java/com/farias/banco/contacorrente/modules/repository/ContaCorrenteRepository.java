package com.farias.banco.contacorrente.modules.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.farias.banco.contacorrente.modules.model.ContaCorrente;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long>, JpaSpecificationExecutor<ContaCorrente> {

	List<ContaCorrente> findByPessoa(Long pessoa);
	
	List<ContaCorrente> findByNumero(Integer numero);
}
