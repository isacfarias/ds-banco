package com.farias.banco.dsprodutos.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.With;

@Getter
@Builder
@With
public class ProdutoValorDTO {

	private Long produto;
	private BigDecimal valor;
}
