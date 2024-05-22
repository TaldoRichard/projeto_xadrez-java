package xadrez.peças;

import Tabuleiro.Posição;
import Tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaDeXadrez;
import xadrez.PeçaDeXadrez;

public class Rei extends PeçaDeXadrez {
	
	private PartidaDeXadrez partida;

	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partida) {
		super(tabuleiro, cor);
		this.partida = partida;
	}
	
	@Override
	public String toString() {
		return "R";
	}

	private boolean podeMover(Posição posição) {
		PeçaDeXadrez p = (PeçaDeXadrez) getTabuleiro().peça(posição);
		return p == null || p.getCor() != getCor();
	}
	
	private boolean testarRookCastling(Posição posição) {
		PeçaDeXadrez p = (PeçaDeXadrez)getTabuleiro().peça(posição);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContadorDeMovimentos() == 0;
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
		
		if (getContadorDeMovimentos() == 0 && !partida.getCheck()) {
			Posição t1 = new Posição(posição.getLinha(), posição.getColuna() + 3);
			if (testarRookCastling(t1)) {
				Posição p1 = new Posição(posição.getLinha(), posição.getColuna() + 1);
				Posição p2 = new Posição(posição.getLinha(), posição.getColuna() + 2);
				if (getTabuleiro().peça(p1) == null && getTabuleiro().peça(p2) == null) {
					mat[posição.getLinha()][posição.getColuna() + 2] = true;
				}
			}
		}
		
		Posição t2 = new Posição(posição.getLinha(), posição.getColuna() - 4);
		if (testarRookCastling(t2)) {
			Posição p1 = new Posição(posição.getLinha(), posição.getColuna() - 1);
			Posição p2 = new Posição(posição.getLinha(), posição.getColuna() - 2);
			Posição p3 = new Posição(posição.getLinha(), posição.getColuna() - 3);
			if (getTabuleiro().peça(p1) == null && getTabuleiro().peça(p2) == null && getTabuleiro().peça(p3) == null) {
				mat[posição.getLinha()][posição.getColuna() + 2] = true;
			}
		}
		return mat;
	}
}
