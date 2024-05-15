package xadrez.peças;

import Tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PeçaDeXadrez;

public class Torre extends PeçaDeXadrez {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	@Override
	public String toString() {
		return "T";
	}

}
