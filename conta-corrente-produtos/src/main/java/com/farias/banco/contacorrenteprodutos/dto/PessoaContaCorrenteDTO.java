package com.farias.banco.contacorrenteprodutos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;


@Getter
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class PessoaContaCorrenteDTO {

	private Long pessoa;
	private Long contaCorrente;
	private Integer score;
}
