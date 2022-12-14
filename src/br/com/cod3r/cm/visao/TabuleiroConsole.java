 package br.com.cod3r.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.cod3r.cm.modelo.Tabuleiro;
import br.com.cod3r.cm.modelo.execeao.ExplosaoExeception;
import br.com.cod3r.cm.modelo.execeao.SairExeception;

public class TabuleiroConsole {
	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		executarJogo();
	}
	
	
	private void executarJogo() {
		try {
			boolean continuar = true;
			
			
			while(continuar) {
				cicloDoJogo();
				
				System.out.println("Outra partida? (S/n)");
				String resposta = entrada.nextLine();
				if(resposta.equalsIgnoreCase("n")) {
					continuar = false;
				}else {
					tabuleiro.reiniciar();
				}
			}
			
		}catch(SairExeception e) {
			System.out.println("Obrigado pela jogatina");
		}finally{
			entrada.close();
		} 
	}
	private void cicloDoJogo() {
		try {
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro.toString());
				String digitado  = capturarValorDigitado("Digite(x, y):");
				Iterator<Integer> xy = Arrays.stream(digitado.split(","))
						.map(e -> Integer.parseInt(e.trim())).iterator();
				
				digitado = capturarValorDigitado("1 --> Abrir\n2 --> (Des)Marcar");
				if("1".equals(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				}else if("2".equals(digitado)) {
					tabuleiro.alterarMarcacao(xy.next(), xy.next());
				}
			}
			
			System.out.println(tabuleiro);
			System.out.println("Você ganhou!");
		}catch(ExplosaoExeception e) {
			System.out.println("Você perdeu");	
			System.out.println(tabuleiro);
		}
	
	}
	
	
	private String capturarValorDigitado(String texto) {
		System.out.println(texto);
		String digitado = entrada.nextLine();
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairExeception();
		}
		return digitado;
		
	}
	
	
}
	