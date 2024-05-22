package xadrez;

import Tabuleiro.Peça;
import Tabuleiro.Posição;
import Tabuleiro.Tabuleiro;

public abstract class PeçaDeXadrez extends Peça{
	
	private Cor cor;
	private int contadorDeMovimentos;

	public PeçaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	protected boolean háUmaPeçaOponente(Posição posição) {
		PeçaDeXadrez p = (PeçaDeXadrez) getTabuleiro().peça(posição);
		return p != null && p.getCor() != cor;
	}
	
	public PosiçãoXadrez getPosiçãoXadrez() {
		return PosiçãoXadrez.fromPosição(posição);
	}
	
	protected void aumentarContador() {
		contadorDeMovimentos++;
	}
	protected void diminuirContador() {
		contadorDeMovimentos--;
	}

	public int getContadorDeMovimentos() {
		return contadorDeMovimentos;
	}

}
