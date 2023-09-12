package com.excepciones;

/**
 *
 * @author Mateo
 */
public class CategoriaEnUsoException extends RuntimeException{

    public CategoriaEnUsoException() {
        super("La categoría se encuentra en uso por un producto");
    }
    
}
