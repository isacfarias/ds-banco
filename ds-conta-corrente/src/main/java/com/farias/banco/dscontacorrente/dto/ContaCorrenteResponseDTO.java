package com.farias.banco.dscontacorrente.dto;

import lombok.*;

import java.util.List;

import io.swagger.annotations.ApiModel;

@Getter
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ContaCorrenteResponse")
public class ContaCorrenteResponseDTO {
	
	private Integer agencia;
	private Integer numero;
	private String tipo;
	private List<ContaCorrenteProdutoDTO> produtos;

}
