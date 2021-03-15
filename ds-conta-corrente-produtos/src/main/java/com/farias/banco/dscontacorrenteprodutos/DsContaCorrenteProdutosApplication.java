package com.farias.banco.dscontacorrenteprodutos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCircuitBreaker
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class DsContaCorrenteProdutosApplication {

	public static void main(String[] args) {
		SpringApplication.run(DsContaCorrenteProdutosApplication.class, args);
	}

}
