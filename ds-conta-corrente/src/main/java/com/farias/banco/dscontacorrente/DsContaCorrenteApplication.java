package com.farias.banco.dscontacorrente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.farias.banco.dscontacorrente.config.AppConfig;
import com.farias.banco.dscontacorrente.config.broker.BrokerInput;

@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class DsContaCorrenteApplication {

	public static void main(String[] args) {
		SpringApplication.run(DsContaCorrenteApplication.class, args);
	}

}
