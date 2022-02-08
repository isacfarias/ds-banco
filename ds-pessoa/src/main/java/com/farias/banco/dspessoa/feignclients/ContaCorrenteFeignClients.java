package com.farias.banco.dspessoa.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(name="ds-conta-corrente", path = "/contacorrente")
public interface ContaCorrenteFeignClients {

}
