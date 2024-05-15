package xadrez;

import Tabuleiro.Posição;
import Tabuleiro.Tabuleiro;
import xadrez.peças.Rei;
import xadrez.peças.Torre;

public class PartidaDeXadrez {
	
	private Tabuleiro tabuleiro;
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8 ,8);
		setupInicial();
	}
	
	public PeçaDeXadrez[][] getPeças(){
		PeçaDeXadrez[][] mat = new PeçaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (PeçaDeXadrez) tabuleiro.peça(i, j);
			}
		}
		return mat;
	}
	
	private void setupInicial() {
		tabuleiro.botarPeça(new Torre(tabuleiro, Cor.branco), new Posição(2, 1));
		tabuleiro.botarPeça(new Rei(tabuleiro, Cor.preto), new Posição(0, 4));
		tabuleiro.botarPeça(new Rei(tabuleiro, Cor.branco), new Posição(5, 3));
	}
}
