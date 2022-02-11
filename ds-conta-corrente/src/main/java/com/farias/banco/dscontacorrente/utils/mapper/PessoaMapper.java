package com.farias.banco.dscontacorrente.utils.mapper;

import org.mapstruct.Mapper;

import com.farias.banco.dscontacorrente.dto.PessoaContaCorrenteResponseDTO;
import com.farias.banco.dscontacorrente.model.Pessoa;

@Mapper
public interface PessoaMapper {

	PessoaContaCorrenteResponseDTO buildPessoaContaCorrenteResponseDTO(Pessoa pessoa);
	
	
	
}
