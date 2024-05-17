package xadrez.peças;

import Tabuleiro.Posição;
import Tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PeçaDeXadrez;

public class Bispo extends PeçaDeXadrez{

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posição p = new Posição(0,0);

		// diagonal ae
		p.setValores(posição.getLinha() - 1, posição.getColuna() - 1);
		while(getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temUmaPeça(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() - 1);	
		}
		if (getTabuleiro().posiçãoExistente(p) && háUmaPeçaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// diagonal ad
		p.setValores(posição.getLinha() - 1, posição.getColuna() + 1);
		while(getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temUmaPeça(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha()- 1, p.getColuna() + 1);	
		}
		if (getTabuleiro().posiçãoExistente(p) && háUmaPeçaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// diagonal be
		p.setValores(posição.getLinha() + 1, posição.getColuna() - 1);
		while(getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temUmaPeça(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posiçãoExistente(p) && háUmaPeçaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// diagonal bd
		p.setValores(posição.getLinha() + 1, posição.getColuna() + 1);
		while(getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temUmaPeça(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() + 1);	
		}
		if (getTabuleiro().posiçãoExistente(p) && háUmaPeçaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "B";
	}
}
