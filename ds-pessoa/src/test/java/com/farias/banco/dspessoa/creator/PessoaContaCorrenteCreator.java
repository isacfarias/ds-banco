package com.farias.banco.dspessoa.creator;

import com.farias.banco.dspessoa.dto.PessoaContaCorrenteDTO;
import static com.farias.banco.dspessoa.creator.PessoaCreator.createUpdatedContaCorrentePF;

public class PessoaContaCorrenteCreator {

    public static PessoaContaCorrenteDTO createPessoaContaCorrenteDTO() {
        return PessoaContaCorrenteDTO.builder()
                .pessoa(createUpdatedContaCorrentePF().getId())
                .contaCorrente(45786l)
                .score(createUpdatedContaCorrentePF().getScore())
                .build();
    }
}
