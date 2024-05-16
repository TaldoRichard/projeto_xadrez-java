package Aplicação;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ExceçãoXadrez;
import xadrez.PartidaDeXadrez;
import xadrez.PeçaDeXadrez;
import xadrez.PosiçãoXadrez;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partida = new PartidaDeXadrez();
		List<PeçaDeXadrez> capturadas = new ArrayList<>();
		
		while (true) {
			try {
				UI.limparTela();
				UI.printPartida(partida, capturadas);
				System.out.println();
				System.out.print("Origem: ");
				PosiçãoXadrez origem = UI.lerPosiçãoXadrez(sc);
				
				boolean[][] movimentosPossiveis = partida.movimentosPossiveis(origem);
				UI.limparTela();
				UI.printTabuleiro(partida.getPeças(), movimentosPossiveis);
				
				System.out.println();
				System.out.print("Destino: ");
				PosiçãoXadrez destino = UI.lerPosiçãoXadrez(sc);
				
				PeçaDeXadrez peçaCapturada = partida.realizarMovimetoDeXadrez(origem, destino);
				
				if (peçaCapturada != null) {
					capturadas.add(peçaCapturada);
				}
			}
			catch (ExceçãoXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}

}
