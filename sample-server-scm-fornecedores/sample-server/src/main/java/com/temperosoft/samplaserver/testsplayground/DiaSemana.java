package com.temperosoft.samplaserver.testsplayground;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiaSemana {
	
	SEGUNDA(1,"SEG"),
	TERCA(2,"TER"),
	QUARTA(3,"QUA"),
	QUINTA(4,"QUI"),
	SEXTA(5,"SEX"),
	SABADO(6,"SAB"),
	DOMINGO(7,"DOM");
	
	private int valor;
	private String simbolo;
	
}
