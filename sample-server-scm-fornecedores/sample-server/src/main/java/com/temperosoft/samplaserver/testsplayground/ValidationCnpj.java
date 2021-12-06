package com.temperosoft.samplaserver.testsplayground;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ValidationCnpj {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String cnpj = sc.nextLine();
		//11.222.333/0001-81
		cnpj = cnpj.replace("/", "");
		cnpj = cnpj.replace(".", "");
		cnpj = cnpj.replace("-", "");
		System.out.println(cnpj);
		
		int[] digitos = new int[15];
		List<Integer> arraySoma = new ArrayList<Integer>();
		
		for (int i = 0; i < cnpj.length(); i++) {
			digitos[i] = cnpj.charAt(i) - 48;
		}
		
		arraySoma.add(digitos[0] * 5);
		arraySoma.add(digitos[1] * 4);
		arraySoma.add(digitos[2] * 3);
		arraySoma.add(digitos[3] * 2);
		arraySoma.add(digitos[4] * 9);
		arraySoma.add(digitos[5] * 8);
		arraySoma.add(digitos[6] * 7);
		arraySoma.add(digitos[7] * 6);
		arraySoma.add(digitos[8] * 5);
		arraySoma.add(digitos[9] * 4);
		arraySoma.add(digitos[10] * 3);
		arraySoma.add(digitos[11] * 2);
		
		int soma1 = arraySoma.stream().reduce(0, Integer::sum);
		int resto1 = soma1 % 11;
		digitos[12] = (resto1 < 2) ? 0 : 11 - resto1;
		
		
		arraySoma = new ArrayList<Integer>();
		
		arraySoma.add(digitos[0] * 6);
		arraySoma.add(digitos[1] * 5);
		arraySoma.add(digitos[2] * 4);
		arraySoma.add(digitos[3] * 3);
		arraySoma.add(digitos[4] * 2);
		arraySoma.add(digitos[5] * 9);
		arraySoma.add(digitos[6] * 8);
		arraySoma.add(digitos[7] * 7);
		arraySoma.add(digitos[8] * 6);
		arraySoma.add(digitos[9] * 5);
		arraySoma.add(digitos[10] * 4);
		arraySoma.add(digitos[11] * 3);
		arraySoma.add(digitos[12] * 2);
		
		int soma2 = arraySoma.stream().reduce(0, Integer::sum);
		int resto2 = soma2 % 11;
		digitos[13] = (resto2 < 2) ? 0 : 11 - resto2;
		
		System.out.println(digitos[12] + "" +  digitos[13]);
		
		if (cnpj.charAt(cnpj.length()-2) != digitos[12] + 48)
			System.out.println("NÃO");
		
		if (cnpj.charAt(cnpj.length()-1) != digitos[13] + 48)
			System.out.println("NÃO");
		
		System.out.println();
		
	}
}
