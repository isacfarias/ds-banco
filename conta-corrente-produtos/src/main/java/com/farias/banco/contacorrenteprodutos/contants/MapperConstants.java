package com.farias.banco.contacorrenteprodutos.contants;

import org.mapstruct.factory.Mappers;

import com.farias.banco.contacorrenteprodutos.utils.mapper.ContaCorrenteProdutosMapper;

public class MapperConstants {
	
	public MapperConstants() {}
	
	public static final ContaCorrenteProdutosMapper contaCorrenteMapper = Mappers.getMapper(ContaCorrenteProdutosMapper.class); 

}
