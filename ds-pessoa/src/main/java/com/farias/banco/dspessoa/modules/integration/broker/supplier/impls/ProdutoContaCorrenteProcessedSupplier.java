package com.farias.banco.dspessoa.modules.integration.broker.supplier.impls;

import com.farias.banco.dspessoa.constants.BrokerConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Supplier;

@Slf4j
public class ProdutoContaCorrenteProcessedSupplier implements Supplier<Message<String>> {

    private BlockingDeque<Message<String>> messages = new LinkedBlockingDeque<>();

    @Override
    public Message<String> get() {
        return messages.poll();
    }

    public void publish(Message<String> message) {
        log.info("Mensagem publicada na exchange [{}] - [{}]", BrokerConstants.EXCHANGE_PRODUTOS_CONTA_CORRENTE_PROCESSED_ORIGEM_NAME, message);
        messages.add(message);
    }
}
