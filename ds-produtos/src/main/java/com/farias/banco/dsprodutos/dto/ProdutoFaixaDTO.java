package com.farias.banco.dsprodutos.dto;
import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.With;

@Getter
@Builder
@With
public class ProdutoFaixaDTO {
	
	private Long id;
	
	private ProdutosTipoDTO produtoTipo;
	
	private Integer scoreMin;
	
	private Integer scoreMax;
	
	private BigDecimal valor;

}