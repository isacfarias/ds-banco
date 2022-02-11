package com.farias.banco.dspessoa.dto;

import lombok.*;

import java.io.Serializable;

import com.farias.banco.dspessoa.enums.StatusEnum;

@Getter
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class PessoaResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	private String cpfCnpj;

	private Integer score;

	private String tipo;
	
	private StatusEnum statusContaCorrente;
	
	private StatusEnum statusProdutos;

}