package com.farias.banco.dscontacorrente.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaCorrenteDTO {
	
	private Integer Agencia;
	private Integer numero;
	private String tipo;
	private List<ContaCorrenteProdutoDTO> produtos;

}
