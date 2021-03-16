package com.farias.banco.dspessoa.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaCorrente implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Integer numero;
	
	private Long pessoa;
	
	private Integer agencia;
	
	private String tipo;
}
