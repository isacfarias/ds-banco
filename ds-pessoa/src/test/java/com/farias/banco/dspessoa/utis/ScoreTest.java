package com.farias.banco.dspessoa.utis;

import org.junit.Test;

import com.farias.banco.dspessoa.utils.ScoreUtils;

public class ScoreTest {

	@Test
	public void test() {
		
		ScoreUtils utis = new ScoreUtils();
		for (int i = 0; i < 10; i++) {
			System.out.println(utis.score());
		}
	}

}
