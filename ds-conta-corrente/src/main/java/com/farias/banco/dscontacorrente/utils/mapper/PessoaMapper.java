package com.farias.banco.dscontacorrente.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.farias.banco.dscontacorrente.dto.PessoaContaCorrenteResponseDTO;
import com.farias.banco.dscontacorrente.modules.model.Pessoa;

@Mapper
public interface PessoaMapper {

	@Mapping(target = "pessoa", source = "id")
	PessoaContaCorrenteResponseDTO buildPessoaContaCorrenteResponseDTO(Pessoa pessoa);
	
	
	
}
