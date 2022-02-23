package com.farias.banco.pessoa.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.farias.banco.pessoa.dto.PessoaRequestDTO;
import com.farias.banco.pessoa.dto.PessoaResponseDTO;
import com.farias.banco.pessoa.modules.model.Pessoa;

@Mapper
public interface PessoaMapper {


	PessoaResponseDTO buildPessoaResponseDTO(Pessoa pessoa);

	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "statusContaCorrente", ignore = true),
		@Mapping(target = "statusProdutos", ignore = true)
	})
	Pessoa buildPessoa(PessoaRequestDTO pessoaRequest);
}
