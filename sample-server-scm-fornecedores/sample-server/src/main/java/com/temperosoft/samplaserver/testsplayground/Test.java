package com.temperosoft.samplaserver.testsplayground;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@SuppressWarnings("unused")
public class Test {
	public static void main(String[] args) {
		test2();
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
