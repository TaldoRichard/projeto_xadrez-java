package Aplicação;

import java.util.Locale;
import java.util.Scanner;

import Tabuleiro.Posição;

public class Programa {

	public static void main(String[] args) {
	
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		Posição pos = new Posição(3, 5);
		System.out.println(pos);
		
		sc.close();
	}

}
