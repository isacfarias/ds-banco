package com.farias.banco.dspessoa.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.farias.banco.dspessoa.DsPessoaApplicationTests;
import com.farias.banco.dspessoa.repository.PessoaRepository;


public class PessoaServiceTest extends DsPessoaApplicationTests {

	@Autowired
	private PessoaService service;
	
	@Autowired
	private PessoaRepository repository;

	@Test
	public void test() {
		
	}

}
