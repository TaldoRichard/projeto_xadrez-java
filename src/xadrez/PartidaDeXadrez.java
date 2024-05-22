package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Tabuleiro.Peça;
import Tabuleiro.Posição;
import Tabuleiro.Tabuleiro;
import xadrez.peças.Bispo;
import xadrez.peças.Cavalo;
import xadrez.peças.Peão;
import xadrez.peças.Rainha;
import xadrez.peças.Rei;
import xadrez.peças.Torre;

public class PartidaDeXadrez {
	
	private Tabuleiro tabuleiro;
	private int turno;
	private Cor jogadorAtual;
	private boolean check;
	private boolean checkMate;
	private PeçaDeXadrez enPassantVulneravel;
	
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
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public PeçaDeXadrez getEnPassantVulneravel() {
		return enPassantVulneravel;
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
		
		if (testeDeCheck(jogadorAtual)) {
			DesfazerMovimento(origem, destino, peçaCapturada);
			throw new ExceçãoXadrez("Voce não pode se colocar em check!");
		}
		
		PeçaDeXadrez peçaMovida = (PeçaDeXadrez)tabuleiro.peça(destino);
		
		check = (testeDeCheck(oponente(jogadorAtual))) ? true : false;
		
		if (testeDeCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}
		else {
			proximoTurno();
		}
		
		// movimento especial enPassant
		if (peçaMovida instanceof Peão && (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
			enPassantVulneravel = peçaMovida;
		}
		else {
			enPassantVulneravel = null;
		}
		
		return (PeçaDeXadrez)peçaCapturada;
	}
	
	private Peça movimentar(Posição origem, Posição destino) {
		PeçaDeXadrez p = (PeçaDeXadrez)tabuleiro.removerPeça(origem);
		p.aumentarContador();
		Peça peçaCapturada = tabuleiro.removerPeça(destino);
		tabuleiro.botarPeça(p, destino);
		
		if (peçaCapturada != null) {
			peçasNoTabuleiro.remove(peçaCapturada);
			peçasCapturadas.add(peçaCapturada);
		}
		
		// #movimento especial aqui lado rei
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posição origemT = new Posição(origem.getLinha(), origem.getColuna() + 3);
			Posição destinoT = new Posição(origem.getLinha(), origem.getColuna() + 1);
			PeçaDeXadrez torre = (PeçaDeXadrez)tabuleiro.removerPeça(origemT);
			tabuleiro.botarPeça(torre, destinoT);
			torre.aumentarContador();
		}
		
		// #movimento especial aqui lado rainha
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posição origemT = new Posição(origem.getLinha(), origem.getColuna() - 4);
			Posição destinoT = new Posição(origem.getLinha(), origem.getColuna() - 1);
			PeçaDeXadrez torre = (PeçaDeXadrez)tabuleiro.removerPeça(origemT);
			tabuleiro.botarPeça(torre, destinoT);
			torre.aumentarContador();
		}
		
		// movimento especial enPassant
		if (p instanceof Peão) {
			if(origem.getColuna() != destino.getColuna() && peçaCapturada == null) {
				Posição posiçãoP;
				if (p.getCor() == Cor.branco) {
					posiçãoP = new Posição(destino.getLinha() + 1, destino.getColuna());
				}
				else {
					posiçãoP = new Posição(destino.getLinha() - 1, destino.getColuna());
				}
				peçaCapturada = tabuleiro.removerPeça(posiçãoP);
				peçasCapturadas.add(peçaCapturada);
				peçasNoTabuleiro.remove(peçaCapturada);
			}
		}
		return peçaCapturada;
	}
	
	private void DesfazerMovimento(Posição origem, Posição destino, Peça peçaCapturada) {
		PeçaDeXadrez p = (PeçaDeXadrez)tabuleiro.removerPeça(destino);
		p.diminuirContador();
		tabuleiro.botarPeça(p, origem);
		
		if (peçaCapturada != null) {
			tabuleiro.botarPeça(peçaCapturada, destino);
			peçasCapturadas.remove(peçaCapturada);
			peçasNoTabuleiro.add(peçaCapturada);
		}
	
		// #movimento especial aqui lado rei
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posição origemT = new Posição(origem.getLinha(), origem.getColuna() + 3);
			Posição destinoT = new Posição(origem.getLinha(), origem.getColuna() + 1);
			PeçaDeXadrez torre = (PeçaDeXadrez)tabuleiro.removerPeça(destinoT);
			tabuleiro.botarPeça(torre, origemT);
			torre.diminuirContador();
		}

		// #movimento especial aqui lado rainha
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posição origemT = new Posição(origem.getLinha(), origem.getColuna() - 4);
			Posição destinoT = new Posição(origem.getLinha(), origem.getColuna() - 1);
			PeçaDeXadrez torre = (PeçaDeXadrez)tabuleiro.removerPeça(destinoT);
			tabuleiro.botarPeça(torre, origemT);
			torre.diminuirContador();
		}
		
		// movimento especial enPassant
		if (p instanceof Peão) {
			if(origem.getColuna() != destino.getColuna() && peçaCapturada == enPassantVulneravel) {
				PeçaDeXadrez peão = (PeçaDeXadrez)tabuleiro.removerPeça(destino);
				Posição posiçãoP;
				if (p.getCor() == Cor.branco) {
					posiçãoP = new Posição(3, destino.getColuna());
				}
				else {
					posiçãoP = new Posição(4, destino.getColuna());
				}
				tabuleiro.botarPeça(peão, posiçãoP);
			}
		}
	
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
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.branco) ? Cor.preto : Cor.branco; 
	}
	
	private PeçaDeXadrez rei(Cor cor) {
		List<Peça> lista = peçasNoTabuleiro.stream().filter(x -> ((PeçaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peça p : lista) {
			if (p instanceof Rei) {
				return (PeçaDeXadrez)p;
			}
		}
		throw new IllegalStateException("Não existe rei desta cor no tabuleiro!");
	}
	
	private boolean testeDeCheck(Cor cor) {
		Posição posiçãoRei = rei(cor).getPosiçãoXadrez().toPosição();
		List<Peça> peçasOponentes = peçasNoTabuleiro.stream().filter(x -> ((PeçaDeXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peça p : peçasOponentes) {
			boolean[][] mat = p.movimentosPossiveis();
			if( mat[posiçãoRei.getLinha()][posiçãoRei.getColuna()]) {
				return true;
			}	
		}
		return false;
	}
	
	private boolean testeDeCheckMate(Cor cor) {
		if (!testeDeCheck(cor)) {
			return false;
		}
		List<Peça> lista = peçasNoTabuleiro.stream().filter(x -> ((PeçaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peça p : lista) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int i=0; i<tabuleiro.getLinhas(); i++) {
				for (int j=0; j<tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posição origem = ((PeçaDeXadrez)p).getPosiçãoXadrez().toPosição();
						Posição destino = new Posição(i,j);
						Peça peçaCapturada = movimentar(origem, destino);
						boolean testeDeCheck = testeDeCheck(cor);
						DesfazerMovimento(origem, destino, peçaCapturada);
						if (!testeDeCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void setupInicial() {
		botarNovaPeça('a', 1, new Torre(tabuleiro, Cor.branco));
		botarNovaPeça('b', 1, new Cavalo(tabuleiro, Cor.branco));
		botarNovaPeça('c', 1, new Bispo(tabuleiro, Cor.branco));
		botarNovaPeça('d', 1, new Rainha(tabuleiro, Cor.branco));
		botarNovaPeça('e', 1, new Rei(tabuleiro, Cor.branco, this));
		botarNovaPeça('h', 1, new Torre(tabuleiro, Cor.branco));
		botarNovaPeça('f', 1, new Bispo(tabuleiro, Cor.branco));
		botarNovaPeça('g', 1, new Cavalo(tabuleiro, Cor.branco));
		botarNovaPeça('a', 2, new Peão(tabuleiro, Cor.branco, this));
		botarNovaPeça('b', 2, new Peão(tabuleiro, Cor.branco, this));
		botarNovaPeça('c', 2, new Peão(tabuleiro, Cor.branco, this));
		botarNovaPeça('d', 2, new Peão(tabuleiro, Cor.branco, this));
		botarNovaPeça('e', 2, new Peão(tabuleiro, Cor.branco, this));
		botarNovaPeça('f', 2, new Peão(tabuleiro, Cor.branco, this));
		botarNovaPeça('g', 2, new Peão(tabuleiro, Cor.branco, this));
		botarNovaPeça('h', 2, new Peão(tabuleiro, Cor.branco, this));

		botarNovaPeça('a', 8, new Torre(tabuleiro, Cor.preto));
		botarNovaPeça('b', 8, new Cavalo(tabuleiro, Cor.preto));
		botarNovaPeça('c', 8, new Bispo(tabuleiro, Cor.preto));
		botarNovaPeça('d', 8, new Rainha(tabuleiro, Cor.preto));
		botarNovaPeça('e', 8, new Rei(tabuleiro, Cor.preto, this));
		botarNovaPeça('f', 8, new Bispo(tabuleiro, Cor.preto));
		botarNovaPeça('g', 8, new Cavalo(tabuleiro, Cor.preto));
		botarNovaPeça('h', 8, new Torre(tabuleiro, Cor.preto));
		botarNovaPeça('a', 7, new Peão(tabuleiro, Cor.preto, this));
		botarNovaPeça('b', 7, new Peão(tabuleiro, Cor.preto, this));
		botarNovaPeça('c', 7, new Peão(tabuleiro, Cor.preto, this));
		botarNovaPeça('d', 7, new Peão(tabuleiro, Cor.preto, this));
		botarNovaPeça('e', 7, new Peão(tabuleiro, Cor.preto, this));
		botarNovaPeça('f', 7, new Peão(tabuleiro, Cor.preto, this));
		botarNovaPeça('g', 7, new Peão(tabuleiro, Cor.preto, this));
		botarNovaPeça('h', 7, new Peão(tabuleiro, Cor.preto, this));
	}
}