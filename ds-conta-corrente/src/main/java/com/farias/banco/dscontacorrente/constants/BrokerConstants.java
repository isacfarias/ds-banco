package com.farias.banco.dscontacorrente.constants;
public interface BrokerConstants {

	/**
	 * Nome das exchanges e queue usadas para ouvir
	 */
	public static final String EXCHANGE_CONTA_CORRENTE_CREATED_ORIGEM_NAME          = "x-conta-corrente-created";
	public static final String EXCHANGE_PRODUTOS_CONTA_CORRENTE_CREATED_ORIGEM_NAME = "x-produtos-conta-corrente-created";
	public static final String EXCHANGE_CONTA_CORRENTE_PROCESSED_ORIGEM_NAME        = "x-conta-corrente-processed";
	public static final String Q_CONTA_CORRENTE_CREATED_ORIGE_NAME = EXCHANGE_CONTA_CORRENTE_CREATED_ORIGEM_NAME +".q-conta-corrente-created";

	/**
	 * Nome do metodo criado no contexto do spring @Bean
	 */
	public static final String EXCHANGE_CONTA_CORRENTE_CREATED          = "subscribeContaCorrenteCreated";
	public static final String EXCHANGE_PRODUTOS_CONTA_CORRENTE_CREATED = "publishProdutosContaCorrenteCreated";
	public static final String EXCHANGE_CONTA_CORRENTE_PROCESSED        = "publishContaCorrenteProcessed";

}