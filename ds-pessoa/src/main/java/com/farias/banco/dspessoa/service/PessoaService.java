package com.farias.banco.dspessoa.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.farias.banco.dspessoa.broker.outbound.PessoaBrokerOutbound;
import com.farias.banco.dspessoa.constants.PessoaConstants;
import com.farias.banco.dspessoa.dto.PessoaContaCorrenteDTO;
import com.farias.banco.dspessoa.dto.PessoaDTORequest;
import com.farias.banco.dspessoa.dto.PessoaDTOResponse;
import com.farias.banco.dspessoa.enums.PessoaTipoEnum;
import com.farias.banco.dspessoa.enums.StatusEnum;
import com.farias.banco.dspessoa.feignclients.ContaCorrenteFeignClients;
import com.farias.banco.dspessoa.model.Pessoa;
import com.farias.banco.dspessoa.repository.PessoaRepository;
import com.farias.banco.dspessoa.repository.specification.PessoaSpecification;
import com.farias.banco.dspessoa.utils.ScoreUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaService {

	private final Logger LOG = LoggerFactory.getLogger(PessoaService.class);

	private final PessoaRepository repository;
	private final ContaCorrenteFeignClients contaCorrenteFeignClients;
	private final ScoreUtils scoreUtils;
	private final PessoaBrokerOutbound brokerOutbound;

	public Page<PessoaDTOResponse> findAll(final Optional<String> nome, final  Optional<String> tipo, final Optional<Integer> score, final Pageable pegeable) {
		return repository.findAll(PessoaSpecification.builder()
				.nome(nome)
				.tipo(tipo)
				.score(score)
				.build(), pegeable).map(this::buildResponse);
	}

	public PessoaDTOResponse cadastrarPessoa(PessoaDTORequest pessoaRequest) {

		final var pessoa = repository.save(buildPessoaRequest(pessoaRequest));

		brokerOutbound.contaCorrentePublish(pessoa);

		return buildResponse(pessoa);
	}

	public void atualizarContaCorrente(PessoaContaCorrenteDTO contaCorrente) {
		repository.save(repository.findById(contaCorrente.getPessoa())
				.map(p -> p.withStatusContaCorrente(StatusEnum.OK))
				.orElseThrow(() -> new EntityNotFoundException("recurso não encontrado")));
	}
	
	public void atualizarContaCorrenteProdutos(PessoaContaCorrenteDTO contaCorrente) {
		repository.save(repository.findById(contaCorrente.getPessoa())
				.map(p -> p.withStatusProdutos(StatusEnum.OK)
					       .withStatusContaCorrente(StatusEnum.OK)
						)
				.orElseThrow(() -> new EntityNotFoundException("recurso não encontrado")));
	}

	private String tipoPessoa(String cpfCnpj) {
		int tipoPessoa = cpfCnpj.length();

		if (tipoPessoa == PessoaConstants.PESSOA_JURIDICA) {
			return PessoaTipoEnum.PJ.name();
		} else if (tipoPessoa <= PessoaConstants.PESSOA_FISICA) {
			return PessoaTipoEnum.PF.name();
		}
		return null;
	}


	private Pessoa buildPessoaRequest(PessoaDTORequest pessoaRequest) {
		return Pessoa.builder()
				.nome(pessoaRequest.getNome())
				.cpfCnpj(pessoaRequest.getCpfCnpj())
				.score(scoreUtils.score())
				.statusContaCorrente(StatusEnum.PENDING)
				.statusProdutos(StatusEnum.PENDING)
				.tipo(tipoPessoa(pessoaRequest.getCpfCnpj()))
				.build();
	}

	private PessoaDTOResponse buildResponse(Pessoa pessoa) {
		return PessoaDTOResponse.builder()
				.id(pessoa.getId())
				.nome(pessoa.getNome())
				.cpfCnpj(pessoa.getCpfCnpj())
				.tipo(pessoa.getTipo())
				.score(pessoa.getScore())
				.statusContaCorrente(pessoa.getStatusContaCorrente())
				.statusProdutos(pessoa.getStatusProdutos())
				.build();
	}


}
