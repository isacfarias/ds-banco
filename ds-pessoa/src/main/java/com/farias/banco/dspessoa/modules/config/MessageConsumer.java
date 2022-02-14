package com.farias.banco.dspessoa.modules.config;

import com.farias.banco.dspessoa.modules.integration.broker.consumer.ContaCorrenteProcessedConsumer;
import com.farias.banco.dspessoa.modules.integration.broker.consumer.ProdutoContaCorrenteProcessedConsumer;
import com.farias.banco.dspessoa.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MessageConsumer {

    private final ObjectMapper mapper;
    private final PessoaService service;

    @Bean
    public ContaCorrenteProcessedConsumer subscribedContaCorrenteProcessed() {
        return new ContaCorrenteProcessedConsumer(mapper, service);
    }

    @Bean
    public ProdutoContaCorrenteProcessedConsumer subscribedProdutosContaCorrenteProcessed() {
        return new ProdutoContaCorrenteProcessedConsumer(mapper, service);
    }
}
