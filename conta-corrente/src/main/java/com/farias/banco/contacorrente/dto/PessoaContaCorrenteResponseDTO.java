package com.farias.banco.contacorrente.dto;

import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class PessoaContaCorrenteResponseDTO {

	private Long pessoa;
	private Long contaCorrente;
	private Integer score;
}
