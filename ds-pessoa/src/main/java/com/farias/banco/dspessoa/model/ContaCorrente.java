package com.farias.banco.dspessoa.model;

import java.io.Serializable;

import lombok.*;

@Getter
@Builder
@With
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
