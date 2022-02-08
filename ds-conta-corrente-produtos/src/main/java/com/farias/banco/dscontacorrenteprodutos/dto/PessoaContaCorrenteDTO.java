package com.farias.banco.dscontacorrenteprodutos.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.With;


@Getter
@Builder
@With
public class PessoaContaCorrenteDTO {

	private Long pessoa;
	private Long contaCorrente;
	private Integer score;
}
