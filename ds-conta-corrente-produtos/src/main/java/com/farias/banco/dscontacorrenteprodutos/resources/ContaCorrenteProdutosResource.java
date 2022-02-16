 package com.farias.banco.dscontacorrenteprodutos.resources;

 import com.farias.banco.dscontacorrenteprodutos.dto.ContaCorrenteProdutoDTO;
 import com.farias.banco.dscontacorrenteprodutos.service.ContaCorrenteProdutosService;
 import io.swagger.annotations.Api;
 import io.swagger.annotations.ApiOperation;
 import io.swagger.annotations.ApiResponse;
 import io.swagger.annotations.ApiResponses;
 import lombok.RequiredArgsConstructor;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Sort;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.RestController;

 import java.util.List;
 import java.util.Optional;

@Api(tags = {"Conta corrente produtos"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/contacorrenteprodutos")
public class ContaCorrenteProdutosResource {
	
	private final ContaCorrenteProdutosService service;


	@ApiOperation(value = "Faz um get para retornar todas as contas corrente produtos cadastradas", response = ContaCorrenteProdutoDTO[].class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Em caso de sucesso.", response = ContaCorrenteProdutoDTO.class)
	})
	@GetMapping
	public Page<List<ContaCorrenteProdutoDTO>> findAll(@RequestParam(required = false) Optional<Long> contaCorrenteProdutosId,
			@RequestParam(required = false) Optional<Long> contaCorrente,
			@RequestParam(required = false) Optional<Integer> ativo,
			@RequestParam(required = false) Optional<Long> produtoTipo,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size,
			@RequestParam(defaultValue = "contaCorrente") String sort,
			@RequestParam(defaultValue = "ASC") Sort.Direction direction) {
		
		return service.findAll(contaCorrenteProdutosId, contaCorrente, ativo, produtoTipo, PageRequest.of(page, size, Sort.by(direction, sort)));
	}

}
