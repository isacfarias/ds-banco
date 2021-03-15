package com.farias.banco.dscontacorrenteprodutos.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutosDTO {

	private Integer produto;
	private BigDecimal valor;
}
