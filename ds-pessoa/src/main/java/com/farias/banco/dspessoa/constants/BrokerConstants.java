package com.farias.banco.dspessoa.constants;

public interface BrokerConstants {

	/**
	 * Nome das exchanges e queue usadas para ouvir
	 */
	String EXCHANGE_CONTA_CORRENTE_CREATED_ORIGEM_NAME             = "x-conta-corrente-created";
	String EXCHANGE_CONTA_CORRENTE_PROCESSED_ORIGEM_NAME           = "x-conta-corrente-processed";
	String EXCHANGE_PRODUTOS_CONTA_CORRENTE_PROCESSED_ORIGEM_NAME  = "x-produtos-conta-corrente-processed";
	
	String Q_CONTA_CORRENTE_PROCESSED_ORIGE_NAME          = EXCHANGE_CONTA_CORRENTE_PROCESSED_ORIGEM_NAME +".q-conta-corrente-processed";
	String Q_PRODUTOS_CONTA_CORRENTE_PROCESSED_ORIGE_NAME = EXCHANGE_PRODUTOS_CONTA_CORRENTE_PROCESSED_ORIGEM_NAME +".q-produtos-conta-corrente-processed";

	/**
	 * Nome do metodo criado no contexto do spring @Bean
	 */
	String EXCHANGE_CONTA_CORRENTE_CREATED             = "publishContaCorrenteCreated-out-0";
	String EXCHANGE_CONTA_CORRENTE_PROCESSED           = "subcribeContaCorrenteProcessed-in-0";
	String EXCHANGE_PRODUTOS_CONTA_CORRENTE_PROCESSED  = "subcribeProdutosContaCorrenteProcessed-in-0";

}
