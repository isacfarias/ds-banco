package com.farias.banco.dscontacorrenteprodutos.modules.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@Getter
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacorrenteprodutos")
public class ContaCorrenteProdutos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ccop_id")
	private Long id;
	
	@Column(name = "prodt_ativo")
	private Integer ativo;
	
	@Column(name = "cco_id")
	private Long contaCorrente;
	
	@Column(name = "prodt_id")
	private Integer produtoTipo;
	
	@Column(name = "ccop_valor")
	private BigDecimal valor;

}
