package com.farias.banco.contacorrente.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.farias.banco.contacorrente.dto.PessoaContaCorrenteResponseDTO;
import com.farias.banco.contacorrente.modules.model.Pessoa;

@Mapper
public interface PessoaMapper {

	@Mapping(target = "pessoa", source = "id")
	PessoaContaCorrenteResponseDTO buildPessoaContaCorrenteResponseDTO(Pessoa pessoa);
	
	
	
}
