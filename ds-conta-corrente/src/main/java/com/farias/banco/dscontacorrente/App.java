package com.farias.banco.dscontacorrente;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.farias.banco.dscontacorrente.config.app.AppConfig;
import com.farias.banco.dscontacorrente.config.broker.BrokerInput;
@EnableRabbit
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
