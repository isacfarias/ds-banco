package com.farias.banco.dsprodutos.dto;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.With;

@Getter
@Builder
@With
@ApiModel(value = "ProdutoFaixa")
public class ProdutoFaixaDTO {
	
	private Long id;
	
	private ProdutosTipoDTO produtoTipo;
	
	private Integer scoreMin;
	
	private Integer scoreMax;
	
	private BigDecimal valor;

}