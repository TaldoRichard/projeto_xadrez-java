package xadrez;

import java.util.ArrayList;
import java.util.List;

import Tabuleiro.Peça;
import Tabuleiro.Posição;
import Tabuleiro.Tabuleiro;
import xadrez.peças.Rei;
import xadrez.peças.Torre;

public class PartidaDeXadrez {
	
	private Tabuleiro tabuleiro;
	private int turno;
	private Cor jogadorAtual;
	
	private List<Peça> peçasNoTabuleiro = new ArrayList<>();
	private List<Peça> peçasCapturadas = new ArrayList<>();
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.branco;
		setupInicial();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getjogadorAtual() {
		return jogadorAtual;
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
	
	public boolean[][] movimentosPossiveis(PosiçãoXadrez posiçãoOrigem){
		Posição posição = posiçãoOrigem.toPosição();
		validarPosiçaoOrigem(posição);
		return tabuleiro.peça(posição).movimentosPossiveis();
	}
	
	public PeçaDeXadrez realizarMovimetoDeXadrez(PosiçãoXadrez posiçãoDeOrigem, PosiçãoXadrez posiçãoDeDestino) {
		Posição origem = posiçãoDeOrigem.toPosição();
		Posição destino = posiçãoDeDestino.toPosição();
		validarPosiçaoOrigem(origem);
		validarPosiçaoDestino(origem, destino);
		Peça peçaCapturada = movimentar(origem, destino);
		proximoTurno();
		return (PeçaDeXadrez)peçaCapturada;
	}
	
	private Peça movimentar(Posição origem, Posição destino) {
		Peça p = tabuleiro.removerPeça(origem);
		Peça peçaCapturada = tabuleiro.removerPeça(destino);
		tabuleiro.botarPeça(p, destino);
		
		if (peçaCapturada != null) {
			peçasNoTabuleiro.remove(peçaCapturada);
			peçasCapturadas.add(peçaCapturada);
		}
		return peçaCapturada;
	}
	
	private void validarPosiçaoOrigem(Posição posição) {
		if (!tabuleiro.temUmaPeça(posição)) {
			throw new ExceçãoXadrez("Não há uma peça na posição de origem!");
		}
		if (jogadorAtual != ((PeçaDeXadrez)tabuleiro.peça(posição)).getCor()) {
			throw new ExceçãoXadrez("A peça escolhida não é sua!");
		}
		if (tabuleiro.peça(posição).existeAlgumMovimento()) {
			throw new ExceçãoXadrez("Não há um movimentos possiveis para a peça escolhida!");
		}
	}
	
	private void validarPosiçaoDestino(Posição origem, Posição destino) {
		if(!tabuleiro.peça(origem).movimentoPossivel(destino)) {
			throw new ExceçãoXadrez("A peça escolhida não pode ser movida para posição escolhida!");
		}
	}
	
	private void botarNovaPeça(char coluna, int linha, PeçaDeXadrez peça) {
		tabuleiro.botarPeça(peça, new PosiçãoXadrez(coluna, linha).toPosição());
		peçasNoTabuleiro.add(peça);
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.branco) ? Cor.preto : Cor.branco; 
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