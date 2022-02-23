package com.farias.banco.pessoa.dto;
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