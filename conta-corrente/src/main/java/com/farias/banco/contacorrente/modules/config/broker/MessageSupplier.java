package com.farias.banco.contacorrente.modules.config.broker;

import com.farias.banco.contacorrente.modules.integration.broker.supplier.impls.ContaCorrenteCreatedSupplier;
import com.farias.banco.contacorrente.modules.integration.broker.supplier.impls.ContaCorrenteProcessedSupplier;
import com.farias.banco.contacorrente.modules.integration.broker.supplier.impls.ProdutosContaCorrenteCreatedSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageSupplier {

    @Bean
    public ContaCorrenteCreatedSupplier publishContaCorrenteCreated() {
        return new ContaCorrenteCreatedSupplier();
    }

    @Bean
    public ContaCorrenteProcessedSupplier publishContaCorrenteProcessed() {
        return new ContaCorrenteProcessedSupplier();
    }

    @Bean
    public ProdutosContaCorrenteCreatedSupplier publishProdutosContaCorrenteCreated() {
        return new ProdutosContaCorrenteCreatedSupplier();
    }
}
