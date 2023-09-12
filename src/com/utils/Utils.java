package com.utils;

import static java.lang.String.valueOf;

public class Utils {
	
	/**
	 * Verifica si el String ingresado es un double.
	 *
	 * @param entrada String a procesar
	 * @return boolean indicando si el String ingresado es un double
	 * longs
	 * @throws NumberFormatException {@inheritDoc}
	 */
	public static boolean esDouble(String entrada) {
		Double aux;
		try {
			aux = Double.parseDouble(entrada);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * Verifica si el String ingresado esta vacio
	 *
	 * @param entrada String a procesar
	 * @return boolean indicando si el String ingresado esta vacio
	 */
	public static boolean estaVacio(String entrada) {
		if (entrada.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * Genera un identificador basado en un String ingresado
	 *
	 * @param stringIngresado String bajo el cual se creara el identificador
	 * @return String identificador compuesto de una letra y dos numeros
	 */
	public static String generarSku(String stringIngresado) {
		int numeroMaximo = 100;
		int numeroMinimo = 999;
		return "" + Character.toUpperCase(stringIngresado.charAt(0)) + valueOf((int) (Math.random() * (numeroMaximo - numeroMinimo + 1) + numeroMinimo));
	}
}
