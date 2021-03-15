package com.farias.banco.dsapigatewayzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class DsApigatewayZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(DsApigatewayZuulApplication.class, args);
	}

}
