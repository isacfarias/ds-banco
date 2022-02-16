package com.farias.banco.dscontacorrenteprodutos.modules.integration.feign;

import java.util.List;

import com.farias.banco.dscontacorrenteprodutos.modules.integration.feign.impl.PageResourcesImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.farias.banco.dscontacorrenteprodutos.modules.config.feing.FeignConfig;
import com.farias.banco.dscontacorrenteprodutos.dto.ProdutosDTO;
import com.farias.banco.dscontacorrenteprodutos.dto.ProdutosTipoDTO;


@Component
@FeignClient(name = "ds-produtos", configuration = { FeignConfig.class })
public interface ProdutosFeignClient {
	
	@GetMapping("/produtosfaixa/score/{score}")
	ResponseEntity<List<ProdutosDTO>> produtosPorScore(@PathVariable Integer score);
	
	@GetMapping("/produtos")
    PageResourcesImpl<ProdutosTipoDTO> produto(@RequestParam(value = "produtoTipoId", required = false) Integer produtoTipoId, Pageable pageable);

}
