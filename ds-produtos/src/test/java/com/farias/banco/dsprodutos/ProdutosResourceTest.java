//package com.farias.banco.dsprodutos;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.farias.banco.dsprodutos.model.ProdutoTipo;
//import com.farias.banco.dsprodutos.service.ProdutosTipoService;
//
//@ActiveProfiles("test")
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//public class ProdutosResourceTest {
//
//	@Autowired
//	private WebApplicationContext wac;
//
//	@LocalServerPort
//	private int port;
//	
//	@MockBean
//	private ProdutosTipoService service;
//	
//	private MockMvc mockMvc;
//
//	@BeforeEach
//	void setUp() throws Exception {
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//	}
//
//	@Test
//	void deveRestonaStatus200_quandoEncontrarResultado() throws Exception {
//		when(service.findAll()).thenReturn(Arrays.asList(new ProdutoTipo(1, "Cheque especial"), new ProdutoTipo(2, "Cartão crédito")));
//		
//		this.mockMvc.perform(get("/produtos")
//                    .contentType(MediaType.APPLICATION_JSON))
//		            .andDo(print())
//		            .andExpect(status().isOk())
//		            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//		            .andExpect(content().string(containsString("id")));
//	}
//	
//	@Test
//	void deveRestonaStatus404_quandoNaoEncontrarResultado() throws Exception {
//		when(service.findAll()).thenReturn(new ArrayList<>());
//		
//		this.mockMvc.perform(get("/produtos")
//                    .contentType(MediaType.APPLICATION_JSON))
//		            .andDo(print())
//		            .andExpect(status().isNotFound());
//	}
//}	
//
