package com.farias.banco.contacorrenteprodutos.dto;

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
public class ProdutosTipoDTO {
	
	private Long id;
	private String descricao;
}
