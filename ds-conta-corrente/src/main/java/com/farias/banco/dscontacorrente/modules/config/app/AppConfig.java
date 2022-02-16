package com.farias.banco.dscontacorrente.modules.config.app;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties("banco")
public class AppConfig {
	
	private final Agencia agencia = new Agencia();
	
	public Agencia getAgencia() {
		return agencia;
	}
	
	@Data
	public static class Agencia {
		private Integer numero;
	}

}
