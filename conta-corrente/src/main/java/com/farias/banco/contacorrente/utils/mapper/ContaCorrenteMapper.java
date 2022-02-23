package com.farias.banco.contacorrente.utils.mapper;

import org.mapstruct.Mapper;

import com.farias.banco.contacorrente.dto.ContaCorrenteResponseDTO;
import com.farias.banco.contacorrente.modules.model.ContaCorrente;
import com.farias.banco.contacorrente.modules.model.Pessoa;

@Mapper
public interface ContaCorrenteMapper {
	
	ContaCorrenteResponseDTO buildContaCorrenteResponseDTO(ContaCorrente contaCorrente);
	
	ContaCorrenteResponseDTO buildContaCorrenteResponseDTO(Pessoa pessoa);

}
