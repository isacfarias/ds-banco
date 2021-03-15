package com.farias.banco.dscontacorrente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.farias.banco.dscontacorrente.config.AppConfig;

@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class DsContaCorrenteApplication {

	public static void main(String[] args) {
		SpringApplication.run(DsContaCorrenteApplication.class, args);
	}

}
