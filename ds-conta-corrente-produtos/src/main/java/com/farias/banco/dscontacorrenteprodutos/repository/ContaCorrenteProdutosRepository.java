package com.farias.banco.dscontacorrenteprodutos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farias.banco.dscontacorrenteprodutos.model.ContaCorrenteProdutos;

@Repository
public interface ContaCorrenteProdutosRepository extends JpaRepository<ContaCorrenteProdutos, Long>{

}
