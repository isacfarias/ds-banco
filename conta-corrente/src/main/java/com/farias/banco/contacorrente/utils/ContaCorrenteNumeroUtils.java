package com.farias.banco.contacorrente.utils;

import org.springframework.stereotype.Component;

@Component
public class ContaCorrenteNumeroUtils {

	
	public int numeroConta() {
		int min = 100000;
		int max = 999999;
		int range = (max - min) +1;
		
		return (int) (Math.random() * range) + min;
	}
}
