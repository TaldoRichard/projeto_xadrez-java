package xadrez;

import Tabuleiro.Tabuleiro;
import xadrez.peças.Rei;
import xadrez.peças.Torre;

public class PartidaDeXadrez {
	
	private Tabuleiro tabuleiro;
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
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
	
	private void botarNovaPeça(char coluna, int linha, PeçaDeXadrez peça) {
		tabuleiro.botarPeça(peça, new PosiçãoXadrez(coluna, linha).toPosição());
	}
	
	private void setupInicial() {
		botarNovaPeça('c', 1, new Torre(tabuleiro, Cor.branco));
		botarNovaPeça('c', 2, new Torre(tabuleiro, Cor.branco));
		botarNovaPeça('d', 2, new Torre(tabuleiro, Cor.branco));
        botarNovaPeça('e', 2, new Torre(tabuleiro, Cor.branco));
        botarNovaPeça('e', 1, new Torre(tabuleiro, Cor.branco));
        botarNovaPeça('d', 1, new Rei(tabuleiro, Cor.branco));

        botarNovaPeça('c', 7, new Torre(tabuleiro, Cor.preto));
        botarNovaPeça('c', 8, new Torre(tabuleiro, Cor.preto));
        botarNovaPeça('d', 7, new Torre(tabuleiro, Cor.preto));
        botarNovaPeça('e', 7, new Torre(tabuleiro, Cor.preto));
        botarNovaPeça('e', 8, new Torre(tabuleiro, Cor.preto));
        botarNovaPeça('d', 8, new Rei(tabuleiro, Cor.preto));
	}
}