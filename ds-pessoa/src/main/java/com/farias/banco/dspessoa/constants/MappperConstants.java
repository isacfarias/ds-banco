package com.farias.banco.dspessoa.constants;

import org.mapstruct.factory.Mappers;

import com.farias.banco.dspessoa.utils.mapper.PessoaMapper;

public class MappperConstants {
	
	public MappperConstants() {}
	
	public static final PessoaMapper pessoaMapper = Mappers.getMapper(PessoaMapper.class);

}
