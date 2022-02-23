package com.farias.banco.pessoa.creator;

import com.farias.banco.pessoa.dto.PessoaContaCorrenteDTO;
import static com.farias.banco.pessoa.creator.PessoaCreator.createUpdatedContaCorrentePF;

public class PessoaContaCorrenteCreator {

    public static PessoaContaCorrenteDTO createPessoaContaCorrenteDTO() {
        return PessoaContaCorrenteDTO.builder()
                .pessoa(createUpdatedContaCorrentePF().getId())
                .contaCorrente(45786l)
                .score(createUpdatedContaCorrentePF().getScore())
                .build();
    }
}
