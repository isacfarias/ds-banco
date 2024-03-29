package com.farias.banco.gateway.config;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Configuration
public class SpringfoxConfig {

    @Bean
    @Primary
    public SwaggerResourcesProvider swaggerResourcesProvider(
            DiscoveryClient discoveryClient,
            @Value("${spring.application.name}") String gateway
    ) {
        return () -> discoveryClient.getServices()
                .stream()
                .filter(service -> !service.equals(gateway))
                .map(service -> {
                    var resource = new SwaggerResource();
                    resource.setName(service);
                    resource.setLocation(String.format("/%s/v2/api-docs", service));
                    return resource;
                })
                .collect(Collectors.toList());
    }

}