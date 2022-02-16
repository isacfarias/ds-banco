package com.farias.banco.dscontacorrente.modules.integration.broker.consumer;

import com.farias.banco.dscontacorrente.constants.BrokerConstants;
import com.farias.banco.dscontacorrente.modules.model.Pessoa;
import com.farias.banco.dscontacorrente.service.ContaCorrenteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;

import java.util.function.Consumer;
import org.apache.commons.lang3.time.StopWatch;

@Slf4j
@RequiredArgsConstructor
public class ContaCorrenteCreatedConsumer implements Consumer<Message<String>> {

    private final ObjectMapper mapper;
    private final ContaCorrenteService service;

    @Override
    public void accept(Message<String> message) {
        var watch = new StopWatch();
        log.info("Mensagem recebida na fila [{}] - headers [{}] - conteudo [{}]", BrokerConstants.Q_CONTA_CORRENTE_CREATED_ORIGE_NAME, message.getHeaders(), message.getPayload());
        try {
            watch.start();
            log.info("[Start] - cadastar conta corrente");
            final var pessoa = mapper.readValue(message.getPayload(), Pessoa.class);
            service.cadastrarContaCorrente(pessoa);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            watch.stop();
            log.info("[Finish] - cadastar conta corrente - [Time] - [{}]", watch.getTime());
        }
    }

}
