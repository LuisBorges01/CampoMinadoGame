package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.cod3r.cm.modelo.execeao.ExplosaoExeception;

public class Campo {
	
	//posições 
	private final int linha;
	private final int coluna;
	
	//quadrado 
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	
	//Deve ser private em razão da lógica por trás da adição de vizinhos.
	
	private List<Campo> vizinhos = new ArrayList<>();
	
	public Campo(int linha, int coluna){
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public boolean adicionarVizinho(Campo vizinho){
		 boolean linhaDiferente = (linha != vizinho.linha);
		 boolean colunaDiferente = (coluna != vizinho.coluna);
		 boolean diagonal = linhaDiferente && colunaDiferente;
		 
		 int deltaLinha = Math.abs(linha - vizinho.linha);
		 int deltaColuna = Math.abs(coluna - vizinho.coluna);
		 int deltaGeral = (deltaLinha + deltaColuna);
		 
		 if(deltaGeral == 1 && !diagonal) {
			 vizinhos.add(vizinho);
			 return true;
		}else if(deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		}else {
			return false;
		}
	}
	
	public void alterarMarcacao() {
		if(!aberto) {
			this.marcado = !marcado;
		}
	}
	

	public boolean abrir(){
		if(!aberto && !marcado) {
			    this.aberto = true;  
		        if(minado) {
			    throw new ExplosaoExeception();
		        }
		        if(vizinhacaSegura()) {
		     	vizinhos.forEach(v -> v.abrir());
		        }
		        return true;
		}else {
			return false;
		}
	}
	
	//----------
	boolean vizinhacaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
	//--------
	public void minar() {
		this.minado = true;	
	}
	//---------
	public boolean isAberto() {
		return aberto; 
	}
	//-------
	public boolean isMinado() {
		return minado;
	}
	//-------
	public boolean isMarcado() {
		return marcado;
	}
    //-------
	public int getLinha() {
		return linha;
	}
    //---------
	public int getColuna() {
		return coluna;
	}
	//-------
	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}	
	//-------
	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}

	long minasVizinhanca() {
		return vizinhos.stream().filter(v -> v.minado).count();
	}
	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}
	
	public String toString() {
		if(marcado) {
			return "x";
		}else if(aberto && minado) {
			return "*";
		}else if(aberto && minasVizinhanca() > 0) {
			return Long.toString(minasVizinhanca());
		}else if(aberto) {
			return " ";	
		}else {
			return "?";
		}
	}
	
	
	
	
	
	
	
	
}
