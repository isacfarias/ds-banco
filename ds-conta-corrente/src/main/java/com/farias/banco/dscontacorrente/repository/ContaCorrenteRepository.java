package com.farias.banco.dscontacorrente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farias.banco.dscontacorrente.model.ContaCorrente;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long> {

	List<ContaCorrente> findByPessoa(Long pessoa);
	
	List<ContaCorrente> findByNumero(Integer numero);
}
