package com.farias.banco.dsprodutos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.With;

@Getter
@Builder
@With
@Entity
@Table(name = "produtotipo")
public class ProdutoTipo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prodt_id")
	private Long id;
	
	@Column(name = "prodt_descricao")
	private String descricao;
	

}
