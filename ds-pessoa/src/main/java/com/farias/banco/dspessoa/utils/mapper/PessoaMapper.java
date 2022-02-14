package com.farias.banco.dspessoa.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.farias.banco.dspessoa.dto.PessoaRequestDTO;
import com.farias.banco.dspessoa.dto.PessoaResponseDTO;
import com.farias.banco.dspessoa.modules.model.Pessoa;

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
