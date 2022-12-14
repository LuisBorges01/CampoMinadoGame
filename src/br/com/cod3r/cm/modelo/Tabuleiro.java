package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.com.cod3r.cm.modelo.execeao.ExplosaoExeception;

public class Tabuleiro {
	private int quantidadeLinhas;
	private int quantidadeColunas;
	private int minasSorteadas;
	List<Campo> campos = new ArrayList<>();
	
	public Tabuleiro(int quantidadeLinhas, int quantidadeColunas, int minasSorteadas) {
		this.quantidadeLinhas = quantidadeLinhas;
		this.quantidadeColunas = quantidadeColunas;
		this.minasSorteadas = minasSorteadas;
	
		gerarCampos();
		associarVizinhos();
		sortearMinas();
	}
	
	public void abrir(int linha, int coluna){ //esse método filtrará a posição da coluna e linha. ao achar
		//O método findFirst acha o primeiro elemento dentro da stream passada.
		try {
			campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst().ifPresent(c -> c.abrir());			
		}catch(ExplosaoExeception e){
			campos.forEach((c -> c.setAberto(true)));
			throw e;
		}
	}
	public void alterarMarcacao(int linha, int coluna){ 
		campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
		.findFirst().ifPresent(c -> c.alterarMarcacao());
	}
	

	private void gerarCampos() {
		for(int linhas = 0; linhas < quantidadeLinhas; linhas++) {
			for(int colunas = 0; colunas < quantidadeColunas; colunas++) {
				 campos.add(new Campo(linhas, colunas));
			}
		}
		
	}
	private void associarVizinhos() {
		for(Campo c1 : campos) {
			for(Campo c2 : campos) {
			c1.adicionarVizinho(c2);
			}
		}
	}
	
	private void sortearMinas() {
		long minasArmadas = 0;
		Predicate<Campo> minado = c -> c.isMinado();
		do {
			int aleatorio = (int)(Math.random()*campos.size());
			campos.get(aleatorio).minar();
			minasArmadas = campos.stream().filter(minado).count();
		}while(minasArmadas < this.minasSorteadas);
		
	}
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}
	public void reiniciar() {
		campos.stream().forEach(c -> c.reiniciar());
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  ");
		for(int c = 0; c < quantidadeColunas; c++) {
			sb.append(" ");
			sb.append(c);
			sb.append(" ");
		}
		sb.append("\n");
		
		int i = 0;
		for(int l = 0; l < quantidadeLinhas; l++) {
			sb.append(l);
			sb.append(" ");
			for(int c  = 0; c < quantidadeColunas; c++) {
				
				sb.append(" ");
				sb.append(campos.get(i));
				sb.append(" ");
				i++;
			}
			sb.append("\n");
		}
		return sb.toString();
	}


}


