package xadrez.peças;

import Tabuleiro.Posição;
import Tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PeçaDeXadrez;

public class Rei extends PeçaDeXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "R";
	}

	private boolean podeMover(Posição posição) {
		PeçaDeXadrez p = (PeçaDeXadrez) getTabuleiro().peça(posição);
		return p == null || p.getCor() != getCor();
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posição p = new Posição(0,0);
		
		//acima
		p.setValores(posição.getLinha() - 1, posição.getColuna());
		if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//abaixo
		p.setValores(posição.getLinha() + 1, posição.getColuna());
		if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//esquerda
		p.setValores(posição.getLinha(), posição.getColuna() - 1);
		if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//direita
		p.setValores(posição.getLinha(), posição.getColuna() + 1);
		if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//eac
		p.setValores(posição.getLinha() - 1, posição.getColuna() - 1);
		if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//dac
		p.setValores(posição.getLinha() - 1, posição.getColuna() + 1);
		if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//eab
		p.setValores(posição.getLinha() + 1, posição.getColuna() - 1);
		if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//dab
		p.setValores(posição.getLinha() + 1, posição.getColuna() + 1);
		if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}
}
