package com.farias.banco.dscontacorrenteprodutos.contants;

import org.mapstruct.factory.Mappers;

import com.farias.banco.dscontacorrenteprodutos.utils.mapper.ContaCorrenteProdutosMapper;

public class MapperConstants {
	
	public MapperConstants() {}
	
	public static final ContaCorrenteProdutosMapper contaCorrenteMapper = Mappers.getMapper(ContaCorrenteProdutosMapper.class); 

}
