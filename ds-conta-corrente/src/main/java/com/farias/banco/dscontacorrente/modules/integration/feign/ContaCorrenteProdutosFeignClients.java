package com.farias.banco.dscontacorrente.modules.integration.feign;

import java.util.List;

import com.farias.banco.dscontacorrente.modules.integration.feign.impl.PageResourcesImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.farias.banco.dscontacorrente.modules.config.feing.FeignConfig;
import com.farias.banco.dscontacorrente.dto.ContaCorrenteProdutoDTO;

@Component
@FeignClient(name="ds-conta-corrente-produtos", path = "/contacorrenteprodutos", configuration = { FeignConfig.class })
public interface ContaCorrenteProdutosFeignClients {

	
	@GetMapping
	PageResourcesImpl<List<ContaCorrenteProdutoDTO>> contaCorrenteProdutos(@RequestParam(value = "contaCorrente", required = false) Long contaCorrente, Pageable pageable);
}
