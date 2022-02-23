package com.farias.banco.pessoa.constants;

import org.mapstruct.factory.Mappers;

import com.farias.banco.pessoa.utils.mapper.PessoaMapper;

public class MappperConstants {
	
	public MappperConstants() {}
	
	public static final PessoaMapper pessoaMapper = Mappers.getMapper(PessoaMapper.class);

}
