package com.farias.banco.dscontacorrenteprodutos.contants;
public interface BrokerConstants {

	/**
	 * Nome das exchanges e queue usadas para ouvir
	 */
	String EXCHANGE_PRODUTOS_CONTA_CORRENTE_CREATED_ORIGEM_NAME = "x-produtos-conta-corrente-created";
	String EXCHANGE_CONTA_CORRENTE_PROCESSED_ORIGEM_NAME        = "x-produtos-conta-corrente-processed";
	String Q_PRODUTOS_CONTA_CORRENTE_CREATED_ORIGE_NAME = EXCHANGE_PRODUTOS_CONTA_CORRENTE_CREATED_ORIGEM_NAME +".q-produtos-conta-corrente-created";

	/**
	 * Nome do metodo criado no contexto do spring @Bean
	 */
	String EXCHANGE_PRODUTOS_CONTA_CORRENTE_CREATED   = "subscribeProdutosContaCorrenteCreated";
	String EXCHANGE_PRODUTOS_CONTA_CORRENTE_PROCESSED = "publishProdutosContaCorrenteProcessed";

}