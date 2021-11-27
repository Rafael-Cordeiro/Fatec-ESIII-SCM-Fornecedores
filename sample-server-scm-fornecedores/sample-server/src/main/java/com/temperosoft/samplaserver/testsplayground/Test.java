package com.temperosoft.samplaserver.testsplayground;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@SuppressWarnings("unused")
public class Test {
	public static void main(String[] args) {
//		test2();
//		imprimeDiaSemana(8);
//		DiaSemana dia = DiaSemana.DOMINGO;
//		System.out.println(dia.toString() + " - " + dia.getValor() + " - " + dia.getSimbolo());

//		TipoFornecimentoEnum tipo = TipoFornecimentoEnum.atSymbol("S");
//		System.out.println(tipo.getLiteral());
		
		FooThread thread1 = new FooThread("Thread #1", 600);
		FooThread thread2 = new FooThread("Thread #2", 900);
		FooThread thread3 = new FooThread("Thread #3", 100);
		
	}
	
	private static void imprimeDiaSemana(int dia) {
		switch (dia) {
		case DiaSemanaConstantes.SEGUNDA:
			System.out.println("Segunda-feira");
			break;
		case DiaSemanaConstantes.TERCA:
			System.out.println("Terça-feira");
			break;
		case DiaSemanaConstantes.QUARTA:
			System.out.println("Quarta-feira");
			break;
		case DiaSemanaConstantes.QUINTA:
			System.out.println("Quinta-feira");
			break;
		case DiaSemanaConstantes.SEXTA:
			System.out.println("Sexta-feira");
			break;
		case DiaSemanaConstantes.SABADO:
			System.out.println("Sábado");
			break;
		case DiaSemanaConstantes.DOMINGO:
			System.out.println("Domingo");
			break;
		default:
			throw new InvalidParameterException("Parâmetro " + dia + " é inválido");
		}
	}
	
	private static void imprimeDiaSemanaEnum(DiaSemana dia) {
		switch (dia) {
		case SEGUNDA:
			System.out.println("Segunda-feira");
			break;
		case TERCA:
			System.out.println("Terça-feira");
			break;
		case QUARTA:
			System.out.println("Quarta-feira");
			break;
		case QUINTA:
			System.out.println("Quinta-feira");
			break;
		case SEXTA:
			System.out.println("Sexta-feira");
			break;
		case SABADO:
			System.out.println("Sábado");
			break;
		case DOMINGO:
			System.out.println("Domingo");
			break;
		default:
			throw new InvalidParameterException("Parâmetro " + dia + " é inválido");
		}
	}
	
	private static void test1() {
		LocalDateTime.now();
		
		System.out.println(
				Base64.getEncoder()
				.encodeToString(
						LocalDateTime.now()
						.format(DateTimeFormatter.ofPattern("yyyyMMddA"))
						.getBytes()
					)
				);
	}
	
	private static void test2() {
		Calculator cal = new Calculator() {
			@Override
			public int calc(int n1, int n2) {
				return n1 * n2;
			}
		};
		Calculator cal2 = new Calculator();
		@SuppressWarnings("deprecation")
		Integer objInt = new Integer(cal.calc(9, 9));
		System.out.println(objInt.intValue());
	}
}
