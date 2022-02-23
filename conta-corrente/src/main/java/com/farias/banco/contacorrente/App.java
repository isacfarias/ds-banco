package com.farias.banco.contacorrente;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.farias.banco.contacorrente.modules.config.app.AppConfig;

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
