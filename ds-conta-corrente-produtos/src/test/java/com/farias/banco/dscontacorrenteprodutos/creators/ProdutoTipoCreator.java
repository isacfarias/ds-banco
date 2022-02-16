package com.farias.banco.dscontacorrenteprodutos.creators;

import com.farias.banco.dscontacorrenteprodutos.dto.ProdutosTipoDTO;

public class ProdutoTipoCreator {
	
	public static ProdutosTipoDTO createProdutosTipoDTO() {
		return ProdutosTipoDTO.builder().id(1l).descricao("Conta corrente").build();
	}

}
