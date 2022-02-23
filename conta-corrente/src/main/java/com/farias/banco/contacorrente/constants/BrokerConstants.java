package com.farias.banco.contacorrente.constants;
public interface BrokerConstants {

	/**
	 * Nome das exchanges e queue usadas para ouvir
	 */
	String EXCHANGE_CONTA_CORRENTE_CREATED_ORIGEM_NAME          = "x-conta-corrente-created";
	String EXCHANGE_PRODUTOS_CONTA_CORRENTE_CREATED_ORIGEM_NAME = "x-produtos-conta-corrente-created";
	String EXCHANGE_CONTA_CORRENTE_PROCESSED_ORIGEM_NAME        = "x-conta-corrente-processed";
	String Q_CONTA_CORRENTE_CREATED_ORIGE_NAME = EXCHANGE_CONTA_CORRENTE_CREATED_ORIGEM_NAME +".q-conta-corrente-created";

	/**
	 * Nome do metodo criado no contexto do spring @Bean
	 */
	String EXCHANGE_CONTA_CORRENTE_CREATED          = "subscribeContaCorrenteCreated";
	String EXCHANGE_PRODUTOS_CONTA_CORRENTE_CREATED = "publishProdutosContaCorrenteCreated";
	String EXCHANGE_CONTA_CORRENTE_PROCESSED        = "publishContaCorrenteProcessed";

}