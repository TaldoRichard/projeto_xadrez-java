package xadrez.peças;

import Tabuleiro.Posição;
import Tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaDeXadrez;
import xadrez.PeçaDeXadrez;

public class Peão extends PeçaDeXadrez {
	
	private PartidaDeXadrez partida;

	public Peão(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partida) {
		super(tabuleiro, cor);
		this.partida = partida;
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
			
			// enPassant brancas
			if (posição.getLinha() == 3) {
				Posição esquerda = new Posição(posição.getLinha(), posição.getColuna() - 1);
				if (getTabuleiro().posiçãoExistente(esquerda) && háUmaPeçaOponente(esquerda) && getTabuleiro().peça(esquerda) == partida.getEnPassantVulneravel()) {
					mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}
				Posição direita = new Posição(posição.getLinha(), posição.getColuna() + 1);
				if (getTabuleiro().posiçãoExistente(direita) && háUmaPeçaOponente(direita) && getTabuleiro().peça(direita) == partida.getEnPassantVulneravel()) {
					mat[direita.getLinha() - 1][direita.getColuna()] = true;
				}
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
			
			// enPassant brancas
			if (posição.getLinha() == 4) {
				Posição esquerda = new Posição(posição.getLinha(), posição.getColuna() - 1);
				if (getTabuleiro().posiçãoExistente(esquerda) && háUmaPeçaOponente(esquerda) && getTabuleiro().peça(esquerda) == partida.getEnPassantVulneravel()) {
					mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}
				Posição direita = new Posição(posição.getLinha(), posição.getColuna() + 1);
				if (getTabuleiro().posiçãoExistente(direita) && háUmaPeçaOponente(direita) && getTabuleiro().peça(direita) == partida.getEnPassantVulneravel()) {
					mat[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}
		}
		
		
		return mat;
	}

	public int getContadorDeMovimentos() {
		return 0;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
