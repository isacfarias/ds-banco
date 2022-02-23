package com.farias.banco.gateway.filter;

import static com.farias.banco.gateway.componente.FilterOrderType.PRE;
import static java.lang.String.format;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CorrelationTrackingPreFilter implements GlobalFilter, Ordered {

    public static final String CORRELATION_ID = "correlation-id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Tracking filter invoked...");

        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders header = request.getHeaders();

        if(hasCorrelationId(header)){
            log.info(format("Tracked request with correlation id %s", header.get(CORRELATION_ID)));
        }else{
            request = exchange.getRequest()
                    .mutate()
                    .header(CORRELATION_ID, generateCorrelationId())
                    .build();
            return chain.filter(exchange.mutate().request(request).build());
        }

        return chain.filter(exchange);
    }
    private boolean hasCorrelationId(HttpHeaders header){
        return header.containsKey(CORRELATION_ID);
    }

    private String generateCorrelationId(){
        return java.util.UUID.randomUUID().toString();
    }

    @Override
    public int getOrder() {
        return PRE.getOrder();
    }
}