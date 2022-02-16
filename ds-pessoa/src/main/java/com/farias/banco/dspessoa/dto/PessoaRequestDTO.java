package com.farias.banco.dspessoa.dto;

import lombok.*;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

@Getter
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PessoaResponse")
public class PessoaRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;

	private String cpfCnpj;

	@ApiParam(hidden = true)
	private Integer score;
	
	@ApiParam(hidden = true)
	private String tipo;

}