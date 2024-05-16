package xadrez;

import Tabuleiro.Peça;
import Tabuleiro.Tabuleiro;

public abstract class PeçaDeXadrez extends Peça{
	
	private Cor cor;

	public PeçaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

}
