package com.farias.banco.dspessoa.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.farias.banco.dspessoa.enums.PessoaTipoEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pes_id")
	private Integer id;
	
	@Column(name ="pes_nome")
	private String nome;
	
	@Column(name ="pes_cpfCnpj")
	private Integer cpfCnpj;
	
	@Column(name ="pes_score")
	private Integer score;
	
	@Column(name = "pes_tipo")
	private PessoaTipoEnum tipo;
	

}
