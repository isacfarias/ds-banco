package com.farias.banco.dsprodutos.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.With;

@Getter
@Builder
@With
public class ProdutosTipoDTO {

	private Long id;
	private String descricao;
}
