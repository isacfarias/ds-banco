package com.farias.banco.pessoa.creator;

import com.farias.banco.pessoa.dto.PessoaRequestDTO;
import com.farias.banco.pessoa.enums.PessoaTipoEnum;
import com.farias.banco.pessoa.modules.model.Pessoa;
import static com.farias.banco.pessoa.constants.MappperConstants.pessoaMapper;

import com.farias.banco.pessoa.dto.PessoaResponseDTO;
import com.farias.banco.pessoa.enums.StatusEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PessoaCreator {

	public static PessoaRequestDTO createRequestPF() {
		return PessoaRequestDTO.builder()
				.cpfCnpj("09152280098")
				.nome("Jorge paulo")
				.build();
	}

	public static Pessoa createPF() {
		return Pessoa.builder()
				.cpfCnpj("09152280098")
				.nome("Jorge paulo")
				.build();
	}

	public static Pessoa createSavedPF() {
		return createPF()
				.withId(1l)
				.withScore(4)
				.withTipo(PessoaTipoEnum.PF.name())
				.withStatusContaCorrente(StatusEnum.PENDING)
				.withStatusProdutos(StatusEnum.PENDING);
	}

	public static Pessoa createUpdatedContaCorrentePF() {
		return createPF()
				.withId(1l)
				.withScore(4)
				.withTipo(PessoaTipoEnum.PF.name())
				.withStatusContaCorrente(StatusEnum.OK)
				.withStatusProdutos(StatusEnum.PENDING);
	}

	public static Pessoa createUpdatedProdutosPF() {
		return createUpdatedContaCorrentePF()
				.withStatusProdutos(StatusEnum.OK);
	}

	public static PessoaResponseDTO createPFtoDTO() {
		return pessoaMapper.buildPessoaResponseDTO(createSavedPF());
	}

	public static PessoaRequestDTO createRequestPJ() {
		return PessoaRequestDTO.builder()
				.cpfCnpj("80107417000197")
				.nome("Gomes & Dias - ME")
				.build();
	}

	public static Pessoa createPJ() {
		return Pessoa.builder().cpfCnpj("Gomes & Dias - ME").nome("80107417000197").build();	
	}

	public static Pessoa createSavedPJ() {
		return createPJ()
				.withId(1l)
				.withScore(4)
				.withTipo(PessoaTipoEnum.PJ.name())
				.withStatusContaCorrente(StatusEnum.PENDING)
				.withStatusProdutos(StatusEnum.PENDING);
	}

	public static Pessoa createUpdatedPJ() {
		return createPJ()
				.withId(1l)
				.withScore(4)
				.withTipo(PessoaTipoEnum.PJ.name())
				.withStatusContaCorrente(StatusEnum.OK)
				.withStatusProdutos(StatusEnum.PENDING);
	}

    public static String creatorJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(createSavedPF());
    }
}
