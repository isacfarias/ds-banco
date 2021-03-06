package com.farias.banco.dspessoa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pes_id")
	private Long id;
	
	@Column(name ="pes_nome")
	private String nome;
	
	@Column(name ="pes_cpfCnpj", unique = true)
	private String cpfCnpj;
	
	@Column(name ="pes_score")
	private Integer score;
	
	@Column(name = "pes_tipo")
	private String tipo;
	

}
