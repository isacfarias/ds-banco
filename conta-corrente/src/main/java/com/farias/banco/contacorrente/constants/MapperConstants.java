package com.farias.banco.contacorrente.constants;

import org.mapstruct.factory.Mappers;

import com.farias.banco.contacorrente.utils.mapper.ContaCorrenteMapper;
import com.farias.banco.contacorrente.utils.mapper.PessoaMapper;

public class MapperConstants {

	public MapperConstants() {}

	public static final ContaCorrenteMapper contaCorrenteMapper = Mappers.getMapper(ContaCorrenteMapper.class);
	public static final PessoaMapper pessoaMapper               = Mappers.getMapper(PessoaMapper.class);


}
