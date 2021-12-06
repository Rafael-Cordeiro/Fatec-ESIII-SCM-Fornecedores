package com.temperosoft.samplaserver.testsplayground;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ValidationCnpj {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String cnpj = sc.nextLine();
		//11.222.333/0001-XX
		List<Integer> digitos = new ArrayList<Integer>();
		List<Integer> soma = new ArrayList<Integer>();
		
		for (int i = 0; i < cnpj.length(); i++) {
			if (i != 2 || i != 6 || i != 10 || i != 15)
				digitos.add(Integer.getInteger(cnpj.substring(i,i+1)));
		}
		
//		digitos.get(arg0)
		
	}
}
