package com.farias.banco.dscontacorrente.resources;

import com.farias.banco.dscontacorrente.creators.PessoaCreator;
import com.farias.banco.dscontacorrente.service.ContaCorrenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.ws.rs.core.MediaType;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContaCorrenteResourceTest {

    @Autowired
    private WebApplicationContext wac;

    @LocalServerPort
    private int port;

    @Autowired
    private ContaCorrenteService service;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    void deveRetonarStatus200_quandoEncontrarResultado() throws Exception {

        this.service.cadastrarContaCorrente(PessoaCreator.createPF());

        this.mockMvc.perform(get("/contacorrente")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.total_elements").value(1));
    }
}
