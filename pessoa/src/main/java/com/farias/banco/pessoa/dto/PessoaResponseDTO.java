package com.farias.banco.pessoa.dto;

import java.io.Serializable;

import com.farias.banco.pessoa.enums.StatusEnum;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@Getter
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PessoaResponse")
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