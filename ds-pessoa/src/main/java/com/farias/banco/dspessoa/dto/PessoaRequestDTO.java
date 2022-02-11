package com.farias.banco.dspessoa.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class PessoaRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;

	private String cpfCnpj;

	private Integer score;

	private String tipo;

}