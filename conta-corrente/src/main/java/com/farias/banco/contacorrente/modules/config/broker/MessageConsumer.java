package com.farias.banco.contacorrente.modules.config.broker;

import com.farias.banco.contacorrente.modules.integration.broker.consumer.ContaCorrenteCreatedConsumer;
import com.farias.banco.contacorrente.service.ContaCorrenteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MessageConsumer {

    private final ObjectMapper mapper;
    private final ContaCorrenteService service;

    @Bean
    public ContaCorrenteCreatedConsumer subscribeContaCorrenteCreated() {
        return new ContaCorrenteCreatedConsumer(mapper, service);
    }
}
