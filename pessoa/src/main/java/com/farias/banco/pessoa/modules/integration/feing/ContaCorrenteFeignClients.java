package com.farias.banco.pessoa.modules.integration.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(name="ds-conta-corrente", path = "/contacorrente")
public interface ContaCorrenteFeignClients {

}
