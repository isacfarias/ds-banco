package com.farias.banco.dscontacorrente.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class ContaCorrenteDTOResponse {
	
	private Integer agencia;
	private Integer numero;
	private String tipo;
	private List<ContaCorrenteProdutoDTO> produtos;

}
