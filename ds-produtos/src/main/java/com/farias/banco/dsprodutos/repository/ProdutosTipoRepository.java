package com.farias.banco.dsprodutos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.farias.banco.dsprodutos.model.ProdutoTipo;

@Repository
public interface ProdutosTipoRepository extends JpaRepository<ProdutoTipo, Long>, JpaSpecificationExecutor<ProdutoTipo> {

}
