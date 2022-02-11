package com.farias.banco.dspessoa.service;

import static com.farias.banco.dspessoa.constants.MappperConstants.pessoaMapper;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.farias.banco.dspessoa.broker.outbound.PessoaBrokerOutbound;
import com.farias.banco.dspessoa.constants.PessoaConstants;
import com.farias.banco.dspessoa.dto.PessoaContaCorrenteDTO;
import com.farias.banco.dspessoa.dto.PessoaRequestDTO;
import com.farias.banco.dspessoa.dto.PessoaResponseDTO;
import com.farias.banco.dspessoa.enums.PessoaTipoEnum;
import com.farias.banco.dspessoa.enums.StatusEnum;
import com.farias.banco.dspessoa.handler.exception.DataBaseException;
import com.farias.banco.dspessoa.repository.PessoaRepository;
import com.farias.banco.dspessoa.repository.specification.PessoaSpecification;
import com.farias.banco.dspessoa.utils.ScoreUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaService {

	private final PessoaRepository repository;
	private final ScoreUtils scoreUtils;
	private final PessoaBrokerOutbound brokerOutbound;

	public Page<PessoaResponseDTO> findAll(final Optional<String> nome, final  Optional<String> tipo, final Optional<Integer> score, final Pageable pegeable) {
		return repository.findAll(PessoaSpecification.builder()
				.nome(nome)
				.tipo(tipo)
				.score(score)
				.build(), pegeable).map(p -> pessoaMapper.buildPessoaResponseDTO(p));
	}

	public PessoaResponseDTO cadastrarPessoa(PessoaRequestDTO pessoaRequest) {
		final var pessoa = repository.save(pessoaMapper.buildPessoa(pessoaRequest)
				.withTipo(tipoPessoa(pessoaRequest.getCpfCnpj()))
				.withScore(scoreUtils.score())
				.withStatusContaCorrente(StatusEnum.PENDING)
				.withStatusProdutos(StatusEnum.PENDING)
				);
		
		brokerOutbound.contaCorrentePublish(pessoa);
		return pessoaMapper.buildPessoaResponseDTO(pessoa);
	}

	public void atualizarContaCorrente(PessoaContaCorrenteDTO contaCorrente) {
		repository.save(repository.findById(contaCorrente.getPessoa())
				.map(p -> p.withStatusContaCorrente(StatusEnum.OK))
				.orElseThrow(() -> new DataBaseException(HttpStatus.NOT_FOUND, "Recurso não encontrado")));
	}
	
	public void atualizarContaCorrenteProdutos(PessoaContaCorrenteDTO contaCorrente) {
		repository.save(repository.findById(contaCorrente.getPessoa())
				.map(p -> p.withStatusProdutos(StatusEnum.OK)
					       .withStatusContaCorrente(StatusEnum.OK)
						)
				.orElseThrow(() -> new DataBaseException(HttpStatus.NOT_FOUND, "Recurso não encontrado")));
	}

	private String tipoPessoa(String cpfCnpj) {
		int tipoPessoa = cpfCnpj.length();

		if (tipoPessoa == PessoaConstants.PESSOA_JURIDICA) {
			return PessoaTipoEnum.PJ.name();
		} else if (tipoPessoa <= PessoaConstants.PESSOA_FISICA) {
			return PessoaTipoEnum.PF.name();
		}
		return PessoaTipoEnum.NA.name();
	}

}
