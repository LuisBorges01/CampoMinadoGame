package br.com.cod3r.CampoMinado;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Teste {

	@Test
	void testarIgualaDois() {
		int a = 1 + 1;
		assertEquals(2, a);
	}
	
	@Test 
	//Erro
	void testarIgualaTres() {
		int a = (2 * 2) - 5;
		assertEquals(10, a);
	}

}
