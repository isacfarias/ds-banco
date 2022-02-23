package com.farias.banco.contacorrenteprodutos.utils.mapper;

import java.math.BigDecimal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.farias.banco.contacorrenteprodutos.contants.ContaCorrenteConstants;
import com.farias.banco.contacorrenteprodutos.dto.ContaCorrenteProdutoDTO;
import com.farias.banco.contacorrenteprodutos.dto.ProdutosDTO;
import com.farias.banco.contacorrenteprodutos.dto.ProdutosTipoDTO;
import com.farias.banco.contacorrenteprodutos.modules.model.ContaCorrenteProdutos;

@Mapper(imports = {BigDecimal.class, ContaCorrenteConstants.class})
public interface ContaCorrenteProdutosMapper {

	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "contaCorrente", ignore = true),
		@Mapping(target = "ativo", expression = "java(produto.getValor().compareTo(new BigDecimal(\"0.0\")) > 0 ? ContaCorrenteConstants.ATIVO : ContaCorrenteConstants.INATIVO)"),
		@Mapping(target = "produtoTipo", source = "produto")
	})
	ContaCorrenteProdutos buildContaCorrenteProdutos(ProdutosDTO produto);

	@Mappings({
		@Mapping(target = "limite", ignore = true),
		@Mapping(target = "produto", source = "descricao")
	})
	ContaCorrenteProdutoDTO buildContaCorrenteProdutosDTO(ProdutosTipoDTO produto);

}
