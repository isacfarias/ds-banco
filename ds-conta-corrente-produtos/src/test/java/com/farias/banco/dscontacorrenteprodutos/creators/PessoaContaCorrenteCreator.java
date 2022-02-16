package com.farias.banco.dscontacorrenteprodutos.creators;

import com.farias.banco.dscontacorrenteprodutos.dto.PessoaContaCorrenteDTO;

public class PessoaContaCorrenteCreator {
	
	public static PessoaContaCorrenteDTO  createContaCorrente() {
		return PessoaContaCorrenteDTO.builder()
			.contaCorrente(1l)
			.pessoa(2l)
			.score(3)
			.build();
	}

}
