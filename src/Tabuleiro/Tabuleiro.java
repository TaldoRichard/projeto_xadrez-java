package Tabuleiro;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peça[][] peças;
	
	
	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new ExceçãoTabuleiro("Erro criando tabuleiro: É necessario que haja pelo menos uma linha e uma coluna!");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		peças = new Peça[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public Peça peça(int linha, int coluna) {
	if (!posiçãoExistente(linha, coluna)) {
		throw new ExceçãoTabuleiro("Posição não existe no tabuleiro!");
		}
		return peças[linha][coluna];
	}
	
	public Peça peça(Posição posição) {
		if (!posiçãoExistente(posição)) {
			throw new ExceçãoTabuleiro("Posição não existe no tabuleiro!");
			}
		return peças[posição.getLinha()][posição.getColuna()];
	}
	
	public void botarPeça(Peça peça, Posição posição){
		if (temUmaPeça(posição)) {
			throw new ExceçãoTabuleiro("Ja tem uma peça na posição " + posição);
		}
		peças[posição.getLinha()][posição.getColuna()] = peça;
		peça.posição = posição;
	}
	
	public boolean posiçãoExistente(int linha, int coluna) {
		return linha >= 0 && linha < linha && coluna >= 0 && coluna < coluna;
	}
	
	public boolean posiçãoExistente(Posição posição) {
		return posiçãoExistente(posição.getLinha(), posição.getColuna());
	}
	
	public boolean temUmaPeça(Posição posição) {
		if (!posiçãoExistente(posição)) {
			throw new ExceçãoTabuleiro("Posição não existe no tabuleiro!");
			}
		return peça(posição) != null;
	}
}
