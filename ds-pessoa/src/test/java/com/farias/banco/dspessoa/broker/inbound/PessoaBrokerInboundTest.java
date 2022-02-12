package com.farias.banco.dspessoa.broker.inbound;


import com.farias.banco.dspessoa.config.broker.BrokerInput;
import com.farias.banco.dspessoa.constants.HeadersConstants;
import com.farias.banco.dspessoa.creator.PessoaCreator;
import com.farias.banco.dspessoa.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.messaging.support.ExecutorSubscribableChannel;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PessoaBrokerInboundTest {

    @InjectMocks
    private PessoaBrokerInboud broker;
    @Mock
    private ObjectMapper mapper;
    @Mock
    private PessoaService service;
    @Mock
    private BrokerInput input;
    private Map<String, Object> headers;

    @BeforeEach
    public void setUp() throws Exception {
        headers = new HashMap<>();
        headers.put(HeadersConstants.AUTHORIZATION_UPPER_CASE, "123");
    }

    @Test
    void subscribeContaCorrentePrecessed() throws Exception {
        when(input.subscribedContaCorrenteProcessed()).thenReturn(new ExecutorSubscribableChannel());
        broker.contaCorrentePrecessed(PessoaCreator.creatorJson(), headers);
    }

    @Test
    void subcribeProdutosContaCorrenteProcessed() throws Exception {
        when(input.subscribedProdutosContaCorrenteProcessed()).thenReturn(new ExecutorSubscribableChannel());
        broker.produtosContaCorrentePrecessed(PessoaCreator.creatorJson(), headers);
    }



}
