package com.farias.banco.dspessoa.broker.outbound;

import com.farias.banco.dspessoa.config.broker.BrokerOutput;
import com.farias.banco.dspessoa.creator.PessoaCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.BlockingQueue;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Referencias:
 * https://docs.spring.io/spring-cloud-stream/docs/Brooklyn.RELEASE/reference/html/_testing.html
 * https://github.com/eugenp/tutorials/blob/master/spring-cloud/spring-cloud-stream/spring-cloud-stream-rabbit/src/test/java/com/baeldung/spring/cloud/stream/rabbit/MyLoggerApplicationIntegrationTest.java
 */

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PessoaBrokerOutboundTest {

    @Autowired
    private PessoaBrokerOutbound broker;

    @Autowired
    private BrokerOutput output;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MessageCollector messageCollector;

    @BeforeEach
    void setUp () { }

    @Test
    void sendMessageContaCorrentePublish() throws JsonProcessingException {
        final var response = PessoaCreator.createPF();
        final var paylaod = mapper.writeValueAsString(response);
        Message<?> message = MessageBuilder.withPayload(paylaod).build();

        broker.contaCorrentePublish(response);
        BlockingQueue<Message<?>> received = messageCollector.forChannel(output.publishContaCorrenteCreated());
        assertThat(received.element().getPayload(), equalTo(paylaod));

    }



}
