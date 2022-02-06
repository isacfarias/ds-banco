package com.farias.banco.dscontacorrente.model;

import java.io.Serializable;

import com.farias.banco.dscontacorrente.enums.ContaTipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@Getter
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String cpfCnpj;
	private Integer score;
	private String tipo;
	
}
