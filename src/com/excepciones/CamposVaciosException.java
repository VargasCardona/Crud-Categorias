package com.excepciones;

public class CamposVaciosException extends RuntimeException {

	public CamposVaciosException() {
		super("Rellene todos los campos");
	}

}
