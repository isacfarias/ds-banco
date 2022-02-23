package com.farias.banco.pessoa.modules.config;

import com.farias.banco.pessoa.modules.integration.broker.supplier.impls.ContaCorrenteCreatedSupplier;
import com.farias.banco.pessoa.modules.integration.broker.supplier.impls.ContaCorrenteProcessedSupplier;
import com.farias.banco.pessoa.modules.integration.broker.supplier.impls.ProdutoContaCorrenteProcessedSupplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Slf4j
@Configuration
@RequiredArgsConstructor
public class MessageSupplier  {

    @Bean
    public ContaCorrenteCreatedSupplier publishContaCorrenteCreated() {
        return new ContaCorrenteCreatedSupplier();
    }

    @Bean
    public ContaCorrenteProcessedSupplier publishContaCorrenteProcessed() {
        return new ContaCorrenteProcessedSupplier();
    }

    @Bean
    public ProdutoContaCorrenteProcessedSupplier publishProdutosContaCorrenteProcessed() {
        return new ProdutoContaCorrenteProcessedSupplier();
    }

}
