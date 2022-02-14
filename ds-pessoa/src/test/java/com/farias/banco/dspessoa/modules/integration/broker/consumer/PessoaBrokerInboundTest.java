package com.farias.banco.dspessoa.modules.integration.broker.consumer;


import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.farias.banco.dspessoa.constants.HeadersConstants;
import com.farias.banco.dspessoa.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
public class PessoaBrokerInboundTest {

    @InjectMocks
    private PessoaMessageConsumer broker;
    @Mock
    private ObjectMapper mapper;
    @Mock
    private PessoaService service;
    @Mock
//    private MessageConsumer input;
    private Map<String, Object> headers;

    @BeforeEach
    public void setUp() throws Exception {
        headers = new HashMap<>();
        headers.put(HeadersConstants.AUTHORIZATION_UPPER_CASE, "123");
    }

    @Test
    void subscribeContaCorrentePrecessed() throws Exception {
//        when(input.subscribedContaCorrenteProcessed()).thenReturn(new ExecutorSubscribableChannel());
//        broker.contaCorrentePrecessed(PessoaCreator.creatorJson(), headers);
    }

    @Test
    void subcribeProdutosContaCorrenteProcessed() throws Exception {
//        when(input.subscribedProdutosContaCorrenteProcessed()).thenReturn(new ExecutorSubscribableChannel());
//        broker.produtosContaCorrentePrecessed(PessoaCreator.creatorJson(), headers);
    }



}
