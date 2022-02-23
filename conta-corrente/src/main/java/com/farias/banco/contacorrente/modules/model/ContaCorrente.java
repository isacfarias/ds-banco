package com.farias.banco.contacorrente.modules.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.farias.banco.contacorrente.enums.ContaTipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
@Entity
@Table(name = "contacorrente")
public class ContaCorrente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name ="cco_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cco_numero", unique = true)
	private Integer numero;
	
	@Column(name = "pes_id")
	private Long pessoa;
	
	@Column(name = "cco_agencia")
	private Integer agencia;
	
	@Column(name = "cco_tipo")
	private ContaTipo tipo;
}
