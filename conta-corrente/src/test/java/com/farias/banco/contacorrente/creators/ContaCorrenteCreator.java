package com.farias.banco.contacorrente.creators;

import com.farias.banco.contacorrente.enums.ContaTipo;
import com.farias.banco.contacorrente.modules.model.ContaCorrente;
import static com.farias.banco.contacorrente.creators.PessoaCreator.createPF;

public class ContaCorrenteCreator {

    public static ContaCorrente createContaCorrente() {
        return ContaCorrente.builder()
                .pessoa(createPF().getId())
                .agencia(5478)
                .tipo(ContaTipo.C)
                .numero(457823)
                .build();
    }

    public static ContaCorrente createContaCorrentePFSaved() {
        return ContaCorrente.builder()
                .id(1l)
                .pessoa(createPF().getId())
                .agencia(5478)
                .tipo(ContaTipo.C)
                .numero(457823)
                .build();
    }

    public static ContaCorrente createContaCorrentePJSaved() {
        return ContaCorrente.builder()
                .id(1l)
                .pessoa(createPF().getId())
                .agencia(5478)
                .tipo(ContaTipo.E)
                .numero(457823)
                .build();
    }

}
