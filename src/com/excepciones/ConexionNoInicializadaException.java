package com.excepciones;

public class ConexionNoInicializadaException extends RuntimeException {

    public ConexionNoInicializadaException() {
        super("No se pudo realizar la conexión");
    }
    
}
