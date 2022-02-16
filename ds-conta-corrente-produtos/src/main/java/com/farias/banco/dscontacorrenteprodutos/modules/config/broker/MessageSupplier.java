package com.farias.banco.dscontacorrenteprodutos.modules.config.broker;

import com.farias.banco.dscontacorrenteprodutos.modules.integration.broker.supplier.impls.ContaCorrenteCreatedSupplier;
import com.farias.banco.dscontacorrenteprodutos.modules.integration.broker.supplier.impls.ProdutosContaCorrenteProcessedSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MessageSupplier {


	@Bean
	public ContaCorrenteCreatedSupplier publishContaCorrenteCreated() {
		return new ContaCorrenteCreatedSupplier();
	}

	@Bean
	public ProdutosContaCorrenteProcessedSupplier publishProdutosContaCorrenteProcessed() {
		return new ProdutosContaCorrenteProcessedSupplier();
	}

}
