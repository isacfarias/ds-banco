package com.farias.banco.dseurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DsEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DsEurekaServerApplication.class, args);
	}

}
