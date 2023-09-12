package com.excepciones;

public class ElementoNoSeleccionadoException extends RuntimeException {

	public ElementoNoSeleccionadoException() {
		super("Seleccione un elemento");
	}

}
