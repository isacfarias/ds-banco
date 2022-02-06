package com.farias.banco.dscontacorrente.dto;

import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class PessoaContaCorrenteDTO {

	private Long pessoa;
	private Long contaCorrente;
	private Integer score;
}
