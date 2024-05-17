package xadrez;

import Tabuleiro.Posição;

public class PosiçãoXadrez {
	
	private int coluna;
	private int linha;
	
	public PosiçãoXadrez(int coluna, int linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new ExceçãoXadrez("Erro instanciando posiçaoXadrez: valores validos são de a1 até h8!");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}
	
	protected Posição toPosição() {
		return new Posição(8 - linha, coluna - 'a');
	}

	protected static PosiçãoXadrez fromPosição(Posição posição) {
		return new PosiçãoXadrez((char)('a' + posição.getColuna()), 8 - posição.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha;
	}

}
