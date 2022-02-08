package com.farias.banco.dscontacorrenteprodutos.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.farias.banco.dscontacorrenteprodutos.dto.ProdutoTipoDTO;
import com.farias.banco.dscontacorrenteprodutos.dto.ProdutosDTO;



@Component
@FeignClient(name = "ds-produtos")
public interface ProdutosFeignClient {
	
	@GetMapping("/produtosfaixa/score/{score}")
	ResponseEntity<List<ProdutosDTO>> produtosPorScore(@PathVariable Integer score);
	
	@GetMapping("/produtos/{id}")
	ResponseEntity<ProdutoTipoDTO> produto(@PathVariable Integer id);

}
