package com.farias.banco.dscontacorrenteprodutos.model;

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
public class ProdutoTipo {
	
	private Integer id;
	private String descricao;
}
