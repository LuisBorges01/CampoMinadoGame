package br.com.cod3r.CampoMinado.modelo;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.modelo.Campo;
import br.com.cod3r.cm.modelo.execeao.ExplosaoExeception;

public class CampoTeste {
	private Campo campo = new Campo(3, 3);
	
	@Test
	void testeVizinhoDistancia1() {
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	@Test
	void testeVizinhoDistanciaDireita() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	@Test
	void testeVizinhoDistancia1Esquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	//Novos métodos
	@Test
	void valorPadraoMarcado() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void alterarMarcacao() {
		campo.alterarMarcacao();
		assertTrue(campo.isMarcado());
		campo.alterarMarcacao();
		assertFalse(campo.isMarcado());
	}
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	@Test
	void abrirMarcado() {
		campo.alterarMarcacao();
		assertFalse(campo.abrir());
	}
	@Test
	void minadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoExeception.class, () -> {     //O funcionamento do asserThrows é para verificar a Execeção que foi lançada está correta com a do parâmetro.
			campo.abrir(); 
			});
	}
	
	@Test 
	void testeAbrirComVizinho() {
		Campo campo11 = new Campo(1, 1);
		Campo campo22 = new Campo(2, 2);
		Campo campoTraicoeiro = new Campo(1, 2);
		campoTraicoeiro.minar();
		
		
		campo22.adicionarVizinho(campoTraicoeiro);
		campo22.adicionarVizinho(campo11);
		campo.adicionarVizinho(campo22);
		
		campo.abrir();//Ele abrirá os próximos em razão do método vizinhançaSegura
		
		assertTrue(campo.adicionarVizinho(campo22)); //Sim, ele pode ser adicionado com vizinho
		assertTrue(campo22.isAberto() && !campo11.isAberto());
		
		
		
		
		
	}
	

}
