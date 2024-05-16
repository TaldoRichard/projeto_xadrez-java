package Aplicação;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.ExceçãoXadrez;
import xadrez.PartidaDeXadrez;
import xadrez.PeçaDeXadrez;
import xadrez.PosiçãoXadrez;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partida = new PartidaDeXadrez();
		
		while (true) {
			try {
				UI.limparTela();
				UI.printTabuleiro(partida.getPeças());
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
