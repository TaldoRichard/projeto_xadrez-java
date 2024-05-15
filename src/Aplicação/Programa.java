package Aplicação;

import java.util.Locale;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;

public class Programa {

	public static void main(String[] args) {
	
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		PartidaDeXadrez partida = new PartidaDeXadrez();
		UI.printTabuleiro(partida.getPeças());
		
		
		sc.close();
	}

}
