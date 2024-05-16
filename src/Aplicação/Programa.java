package Aplicação;

import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PeçaDeXadrez;
import xadrez.PosiçãoXadrez;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partida = new PartidaDeXadrez();
		
		while (true) {
			UI.printTabuleiro(partida.getPeças());
			System.out.println();
			System.out.print("Origem: ");
			PosiçãoXadrez origem = UI.lerPosiçãoXadrez(sc);
			
			System.out.println();
			System.out.print("Destino: ");
			PosiçãoXadrez destino = UI.lerPosiçãoXadrez(sc);
			
			PeçaDeXadrez peçaCapturada = partida.realizarMovimetoDeXadrez(origem, destino);
		}
	}

}
