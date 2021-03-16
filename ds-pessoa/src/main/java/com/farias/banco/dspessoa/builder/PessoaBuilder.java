package com.farias.banco.dspessoa.builder;

import com.farias.banco.dspessoa.constants.PessoaConstants;
import com.farias.banco.dspessoa.enums.PessoaTipoEnum;
import com.farias.banco.dspessoa.model.Pessoa;
import com.farias.banco.dspessoa.utils.ScoreUtils;

public class PessoaBuilder {
	
	Pessoa pessoa;
	
	public PessoaBuilder() {
		pessoa = new Pessoa();
	}
	
	public PessoaBuilder pessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
		return this;
	}
	
	public PessoaBuilder score(ScoreUtils score) {
		this.pessoa.setScore(score.score());
		return this;
	}
	
	private void tipoPessoa() {
		int tipoPessoa = pessoa.getCpfCnpj().length();
		
		if (tipoPessoa == PessoaConstants.PESSOA_JURIDICA) {
			pessoa.setTipo(PessoaTipoEnum.PJ.name());
		} else if (tipoPessoa <= PessoaConstants.PESSOA_FISICA) {
			pessoa.setTipo(PessoaTipoEnum.PF.name());
		}
	}

	public Pessoa builder() {
		tipoPessoa();
		return this.pessoa;
	}

	

}
