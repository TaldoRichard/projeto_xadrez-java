package Aplicação;

import java.util.Locale;
import java.util.Scanner;

import Tabuleiro.Tabuleiro;

public class Programa {

	public static void main(String[] args) {
	
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		Tabuleiro tab = new Tabuleiro(8, 8);
		
		
		sc.close();
	}

}
