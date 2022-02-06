package com.farias.banco.dspessoa.constants;

public interface BrokerConstants {

	/**
	 * Nome das exchanges e queue usadas para ouvir
	 */
	public static final String EXCHAGE_CONTA_CORRENTE_CREATED_ORIGEM_NAME = "x-conta-corrente-created";
	public static final String EXCHAGE_CONTA_CORRENTE_PROCESSED_ORIGEM_NAME = "x-conta-corrente-processed";
	public static final String Q_CONTA_CORRENTE_PROCESSED_ORIGE_NAME = EXCHAGE_CONTA_CORRENTE_PROCESSED_ORIGEM_NAME +".q-conta-corrente-processed";

	/**
	 * Nome do metodo criado no contexto do spring @Bean
	 */
	public static final String EXCHAGE_CONTA_CORRENTE_CREATED = "publishContaCorrenteCreated";
	public static final String EXCHAGE_CONTA_CORRENTE_PROCESSED = "subcribeContaCorrenteProcessed";

}
