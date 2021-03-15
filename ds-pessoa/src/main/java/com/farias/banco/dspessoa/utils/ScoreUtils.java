package com.farias.banco.dspessoa.utils;

import org.springframework.stereotype.Component;

@Component
public class ScoreUtils {

	
	public int score() {
		int min = 0;
		int max = 9;
		int range = (max - min) +1;
		
		return (int) (Math.random() * range) + min;
	}
}
