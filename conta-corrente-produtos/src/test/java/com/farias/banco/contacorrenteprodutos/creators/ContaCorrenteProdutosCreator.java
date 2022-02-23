package com.farias.banco.contacorrenteprodutos.creators;

import com.farias.banco.contacorrenteprodutos.modules.model.ContaCorrenteProdutos;
import static com.farias.banco.contacorrenteprodutos.creators.PessoaContaCorrenteCreator.createContaCorrente;

import java.math.BigDecimal;

public class ContaCorrenteProdutosCreator {
	
	public static ContaCorrenteProdutos createContaCorrenteProdutos() {
		return ContaCorrenteProdutos.builder()
				.id(1l)
				.ativo(1)
				.contaCorrente(createContaCorrente().getContaCorrente())
				.produtoTipo(1)
				.valor(new BigDecimal("0.0"))
				.build();
	}

}
