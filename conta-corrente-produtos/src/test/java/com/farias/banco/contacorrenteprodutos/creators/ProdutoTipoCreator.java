package com.farias.banco.contacorrenteprodutos.creators;

import com.farias.banco.contacorrenteprodutos.dto.ProdutosTipoDTO;

public class ProdutoTipoCreator {
	
	public static ProdutosTipoDTO createProdutosTipoDTO() {
		return ProdutosTipoDTO.builder().id(1l).descricao("Conta corrente").build();
	}

}
