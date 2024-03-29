package com.farias.banco.produtos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@Getter
@Builder
@With
@NoArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
@Entity
@Table(name = "produtotipo")
public class ProdutoTipo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prodt_id")
	private Long id;
	
	@Column(name = "prodt_descricao")
	private String descricao;
	

}
