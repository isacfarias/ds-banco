package com.farias.banco.dscontacorrente.utils.mapper;

import org.mapstruct.Mapper;

import com.farias.banco.dscontacorrente.dto.ContaCorrenteResponseDTO;
import com.farias.banco.dscontacorrente.model.ContaCorrente;
import com.farias.banco.dscontacorrente.model.Pessoa;

@Mapper
public interface ContaCorrenteMapper {
	
	ContaCorrenteResponseDTO buildContaCorrenteResponseDTO(ContaCorrente contaCorrente);
	
	ContaCorrenteResponseDTO buildContaCorrenteResponseDTO(Pessoa pessoa);

}
