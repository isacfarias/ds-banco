package com.farias.banco.dsprodutos.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.With;

@Getter
@Builder
@With
@Entity
@Table(name = "produtofaixa")
public class ProdutoFaixa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prof_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "prodt_id")
	private ProdutoTipo produtoTipo;
	
	@Column(name = "prof_scoremin")
	private Integer scoreMin;
	
	@Column(name = "prof_scoremax")
	private Integer scoreMax;
	
	@Column(name = "prof_valor")
	private BigDecimal valor;

}
