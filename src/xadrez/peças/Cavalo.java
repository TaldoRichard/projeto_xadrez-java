package xadrez.peças;

import Tabuleiro.Posição;
import Tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PeçaDeXadrez;

public class Cavalo extends PeçaDeXadrez{

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posição p = new Posição(0,0);

		p.setValores(posição.getLinha() - 1, posição.getColuna() - 2);
		if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posição.getLinha() + 1, posição.getColuna() - 2);
		if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posição.getLinha()  - 1 , posição.getColuna() + 2);
		if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posição.getLinha() + 1, posição.getColuna() + 2);
		if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posição.getLinha() - 2, posição.getColuna() - 1);
		if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posição.getLinha() - 2, posição.getColuna() + 1);
		if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posição.getLinha() + 2, posição.getColuna() - 1);
		if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posição.getLinha() + 2, posição.getColuna() + 1);
		if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}
	
	private boolean podeMover(Posição posição) {
		PeçaDeXadrez p = (PeçaDeXadrez) getTabuleiro().peça(posição);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public String toString() {
		return "C";
	}
}
