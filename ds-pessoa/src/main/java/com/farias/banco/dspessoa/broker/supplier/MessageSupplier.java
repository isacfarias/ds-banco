package com.farias.banco.dspessoa.broker.supplier;

import com.farias.banco.dspessoa.constants.BrokerConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;
@Slf4j
@Configuration
public class MessageSupplier {

    @Bean
    public Sinks.Many<Message<String>> many() {
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<Message<String>>> publishContaCorrenteCreated(Sinks.Many<Message<String>> many) {
        return () -> many.asFlux()
                .doOnNext(m -> log.info("Mensagem publicada na exchane [{}] - [{}]", BrokerConstants.EXCHANGE_CONTA_CORRENTE_CREATED_ORIGEM_NAME, m))
                .doOnError(t -> log.error("Error encountered", t));
    }

    @Bean
    public Supplier<Flux<Message<String>>> publishContaCorrenteProcessed(Sinks.Many<Message<String>> many) {
        return () -> many.asFlux()
                .doOnNext(m -> log.info("Mensagem publicada na exchane [{}] - [{}]", BrokerConstants.EXCHANGE_CONTA_CORRENTE_PROCESSED_ORIGEM_NAME, m))
                .doOnError(t -> log.error("Error encountered", t));
    }

    @Bean
    public Supplier<Flux<Message<String>>> publishProdutosContaCorrenteProcessed(Sinks.Many<Message<String>> many) {
        return () -> many.asFlux()
                .doOnNext(m -> log.info("Mensagem publicada na exchane [{}] - [{}]", BrokerConstants.EXCHANGE_PRODUTOS_CONTA_CORRENTE_PROCESSED_ORIGEM_NAME, m))
                .doOnError(t -> log.error("Error encountered", t));
    }

}
