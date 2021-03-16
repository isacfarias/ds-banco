package com.farias.banco.dspessoa.resource;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.farias.banco.dspessoa.repository.PessoaRepository;

@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PessoaResourceTest {
	
	@Autowired
	private WebApplicationContext wac;

	@LocalServerPort
	private int port;
	
	@Autowired 
	private PessoaRepository repository;
	
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test @Order(1) 
	void deveRestonaStatus200_quandoEncontrarResultado() throws Exception {
		this.mockMvc.perform(get("/pessoa")
                    .contentType(MediaType.APPLICATION_JSON))
		            .andDo(print())
		            .andExpect(status().isOk())
		            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		            .andExpect(content().string(containsString("id")));
	}
	
	@Test @Order(2)
	void deveRestonaStatus400_quandoCadastarCpfCnpjJaExistente() throws Exception {
		this.mockMvc.perform(post("/pessoa")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"nome\": \"João batista\", \"cpfCnpj\": \"09152280098\" }") 
                    .accept(MediaType.APPLICATION_JSON))
		            .andDo(print())
		            .andExpect(status().isBadRequest());
	}
	
	@Test @Order(3)
	void deveRestonaStatus204_quandoNaoEncontrarResultado() throws Exception {
		repository.deleteAll();
		
		this.mockMvc.perform(get("/pessoa")
                    .contentType(MediaType.APPLICATION_JSON))
		            .andDo(print())
		            .andExpect(status().isNoContent());
	}
}
