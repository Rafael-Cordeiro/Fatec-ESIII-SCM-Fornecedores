package com.temperosoft.scmfornecedores.core.business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class Test {
	public static void main(String[] args) {
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
}
