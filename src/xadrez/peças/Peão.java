package xadrez.peças;

import Tabuleiro.Posição;
import Tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PeçaDeXadrez;

public class Peão extends PeçaDeXadrez {

	public Peão(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posição p = new Posição(0,0);
		
		if(getCor() == Cor.branco) {
			p.setValores(posição.getLinha() - 1 , posição.getColuna());
			if (getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temUmaPeça(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posição.getLinha() - 2 , posição.getColuna());
			Posição p2 = new Posição(posição.getLinha() - 1, posição.getColuna());
			if (getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temUmaPeça(p) && getTabuleiro().posiçãoExistente(p2) && !getTabuleiro().temUmaPeça(p2) && getContadorDeMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posição.getLinha() - 1 , posição.getColuna() - 1);
			if (getTabuleiro().posiçãoExistente(p) && háUmaPeçaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posição.getLinha() - 1 , posição.getColuna() + 1);
			if (getTabuleiro().posiçãoExistente(p) && háUmaPeçaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		else {
			p.setValores(posição.getLinha() + 1 , posição.getColuna());
			if (getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temUmaPeça(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posição.getLinha() + 2 , posição.getColuna());
			Posição p2 = new Posição(posição.getLinha() + 1, posição.getColuna());
			if (getTabuleiro().posiçãoExistente(p) && !getTabuleiro().temUmaPeça(p) && getTabuleiro().posiçãoExistente(p2) && !getTabuleiro().temUmaPeça(p2) && getContadorDeMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posição.getLinha() + 1 , posição.getColuna() - 1);
			if (getTabuleiro().posiçãoExistente(p) && háUmaPeçaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posição.getLinha() + 1 , posição.getColuna() + 1);
			if (getTabuleiro().posiçãoExistente(p) && háUmaPeçaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		return mat;
	}

	private int getContadorDeMovimentos() {
		return 0;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
