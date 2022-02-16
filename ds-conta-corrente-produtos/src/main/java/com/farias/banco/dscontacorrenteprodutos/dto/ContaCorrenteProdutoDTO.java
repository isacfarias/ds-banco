package com.farias.banco.dscontacorrenteprodutos.dto;

import java.math.BigDecimal;

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
@ApiModel(value = "ContaCorrenteProduto")
public class ContaCorrenteProdutoDTO {
	
	private String produto;
	private BigDecimal limite;

}
