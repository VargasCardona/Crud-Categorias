package com.excepciones;

public class DoubleInvalidoException extends RuntimeException {

	public DoubleInvalidoException() {
		super("Ingrese un precio valido");
	}

}
