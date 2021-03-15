package com.farias.banco.dscontacorrente.builder;

import com.farias.banco.dscontacorrente.enums.ContaTipo;
import com.farias.banco.dscontacorrente.enums.PessoaTipoEnum;
import com.farias.banco.dscontacorrente.model.ContaCorrente;

public class ContaCorrenteBuilder {

	private ContaCorrente contaCorrente;

	public ContaCorrenteBuilder() {
		this.contaCorrente = new ContaCorrente();
	}

	public ContaCorrenteBuilder agencia(Integer agencia) {
		this.contaCorrente.setAgencia(agencia);
		return this;
	}

	public ContaCorrenteBuilder numero(Integer numero) {
		this.contaCorrente.setNumero(numero);
		return this;
	}

	public ContaCorrenteBuilder pessoa(Long pessoa) {
		this.contaCorrente.setPessoa(pessoa);
		return this;
	}

	public ContaCorrenteBuilder contaTipo(String pessoaTipo) {
		ContaTipo tipo = null;
		if (pessoaTipo.equals(PessoaTipoEnum.PF.name())) {
			tipo = ContaTipo.C;
		} else if (pessoaTipo.equals(PessoaTipoEnum.PJ.name())) {
			tipo = ContaTipo.E;
		}

		this.contaCorrente.setTipo(tipo);
		return this;
	}

	public ContaCorrente builder() {
		return this.contaCorrente;
	}



}
