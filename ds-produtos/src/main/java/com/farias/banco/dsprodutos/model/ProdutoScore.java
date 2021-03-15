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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produtoscore")
public class ProdutoScore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pros_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "prodt_id")
	private ProdutoTipo produtoTipo;
	
	@Column(name = "pros_scoremin")
	private Integer scoreMin;
	
	@Column(name = "pros_scoremax")
	private Integer scoreMax;
	
	@Column(name = "pros_valor")
	private BigDecimal valor;

}
