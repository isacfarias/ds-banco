package com.farias.banco.dscontacorrente.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class ContaCorrenteProdutoDTO {
	
	private String produto;
	private BigDecimal limite;

}
