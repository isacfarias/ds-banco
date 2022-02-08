package com.farias.banco.dscontacorrenteprodutos.dto;

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
public class ProdutoTipoDTO {
	
	private Integer id;
	private String descricao;
}
