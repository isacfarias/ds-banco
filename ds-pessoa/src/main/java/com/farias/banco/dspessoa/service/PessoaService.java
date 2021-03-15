package com.farias.banco.dspessoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farias.banco.dspessoa.constants.PessoaConstants;
import com.farias.banco.dspessoa.enums.PessoaTipoEnum;
import com.farias.banco.dspessoa.model.Pessoa;
import com.farias.banco.dspessoa.repository.PessoaRepository;
import com.farias.banco.dspessoa.utils.ScoreUtils;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;
	
	@Autowired
	private ScoreUtils scoreUtils;
	
	public Pessoa cadastrarPessoa(Pessoa pessoa) {
		
		int tipoPessoa = pessoa.getCpfCnpj().length();
		
		if (tipoPessoa == PessoaConstants.PESSOA_JURIDICA) {
			pessoa.setTipo(PessoaTipoEnum.PJ);
		} else if (tipoPessoa <= PessoaConstants.PESSOA_FISICA) {
			pessoa.setTipo(PessoaTipoEnum.PF);
		}
		
		pessoa.setScore(scoreUtils.score());
		repository.save(pessoa);
		return pessoa;
	}
	
}
