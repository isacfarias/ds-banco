package com.farias.banco.contacorrenteprodutos.modules.integration.broker.consumer;

import com.farias.banco.contacorrenteprodutos.contants.BrokerConstants;
import com.farias.banco.contacorrenteprodutos.dto.PessoaContaCorrenteDTO;
import com.farias.banco.contacorrenteprodutos.service.ContaCorrenteProdutosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
public class ProdutosContaCorrenteCreatedConsumer implements Consumer<Message<String>> {

    private final ObjectMapper mapper;
    private final ContaCorrenteProdutosService service;

    @Override
    public void accept(Message<String> message) {
        log.info("Mensagem recebida na fila [{}] - headers [{}] - conteudo [{}]", BrokerConstants.Q_PRODUTOS_CONTA_CORRENTE_CREATED_ORIGE_NAME, message.getHeaders(), message.getPayload());
        try {
            log.info("[Start] - cadastar conta corrente");
            final var pessoaContaCorrente = mapper.readValue(message.getPayload(), PessoaContaCorrenteDTO.class);
            service.vincularProdutosContaCorrente(pessoaContaCorrente);
            log.info("[Stop] - cadastar conta corrente");
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

}
