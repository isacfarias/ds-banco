package com.farias.banco.dscontacorrente.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaCorrenteProdutoDTO {
	
	private String produto;
	private BigDecimal limite;

}
