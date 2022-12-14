package br.com.cod3r.CampoMinado.modelo;

import br.com.cod3r.cm.modelo.Tabuleiro;
import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;


import br.com.cod3r.cm.modelo.Campo;
import br.com.cod3r.cm.modelo.execeao.ExplosaoExeception;


@SuppressWarnings("unused")
public class TabuleiroTest {
	private Tabuleiro tabuleiro = new Tabuleiro(10,10,4);
	
	@Test
	public void testeAbrir() {
		tabuleiro.abrir(2, 3);
		System.out.println(tabuleiro);
		tabuleiro.abrir(5, 7);
		System.out.println(tabuleiro);
		tabuleiro.abrir(3, 9);
		System.out.println(tabuleiro);
	}
	@Test
	public void testeMarcar() {
		tabuleiro.alterarMarcacao(2, 3);
		System.out.println(tabuleiro);
	}

}
