package com.farias.banco.contacorrente.creators;

import com.farias.banco.contacorrente.enums.PessoaTipoEnum;
import com.farias.banco.contacorrente.modules.model.Pessoa;

public class PessoaCreator {

	public static Pessoa createPF() {
		return Pessoa.builder()
				.id(1l)
				.cpfCnpj("09152280098")
				.nome("Jorge paulo")
				.tipo(PessoaTipoEnum.PF.name())
				.score(4)
				.build();
	}

	public static Pessoa createPJ() {
		return Pessoa.builder()
				.id(1l)
				.cpfCnpj("80107417000197")
				.nome("Gomes & Dias - ME")
				.tipo(PessoaTipoEnum.PJ.name())
				.score(4)
				.build();
	}
}
