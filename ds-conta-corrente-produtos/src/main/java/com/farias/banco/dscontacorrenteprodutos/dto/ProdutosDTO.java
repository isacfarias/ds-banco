package com.farias.banco.dscontacorrenteprodutos.dto;

import java.math.BigDecimal;

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
public class ProdutosDTO {

	private Integer produto;
	private BigDecimal valor;
}
