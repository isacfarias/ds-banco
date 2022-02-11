package com.farias.banco.dscontacorrente.dto;

import lombok.*;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;

@Getter
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ContaCorrenteProduto")
public class ContaCorrenteProdutoDTO {
	
	private String produto;
	private BigDecimal limite;

}
