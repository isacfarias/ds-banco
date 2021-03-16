package com.farias.banco.dscontacorrenteprodutos.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoScore {
	
	private Integer id;
	private Integer produtoTipo;
	private Integer scoreMin;
	private Integer scoreMax;
	private BigDecimal valor;
}
