package com.farias.banco.dsprodutos.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.With;

@Getter
@Builder
@With
@ApiModel(value = "ProdutoValor")
public class ProdutoValorDTO {

	private Long produto;
	private BigDecimal valor;
}
