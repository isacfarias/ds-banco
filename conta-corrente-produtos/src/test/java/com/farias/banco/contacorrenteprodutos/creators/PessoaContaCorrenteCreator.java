package com.farias.banco.contacorrenteprodutos.creators;

import com.farias.banco.contacorrenteprodutos.dto.PessoaContaCorrenteDTO;

public class PessoaContaCorrenteCreator {
	
	public static PessoaContaCorrenteDTO  createContaCorrente() {
		return PessoaContaCorrenteDTO.builder()
			.contaCorrente(1l)
			.pessoa(2l)
			.score(3)
			.build();
	}

}
