package com.farias.banco.pessoa.modules.integration.broker.supplier.impls;

import com.farias.banco.pessoa.constants.BrokerConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Supplier;

@Slf4j
public class ContaCorrenteProcessedSupplier implements Supplier<Message<String>> {

    private BlockingDeque<Message<String>> messages = new LinkedBlockingDeque<>();

    @Override
    public Message<String> get() {
        return messages.poll();
    }

    public void publish(Message<String> message) {
        log.info("Mensagem publicada na exchange [{}] - [{}]", BrokerConstants.EXCHANGE_CONTA_CORRENTE_PROCESSED_ORIGEM_NAME, message);
        messages.add(message);
    }
}
