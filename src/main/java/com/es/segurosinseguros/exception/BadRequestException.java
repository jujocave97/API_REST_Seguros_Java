package com.es.segurosinseguros.exception;

public class BadRequestException extends RuntimeException{
    public static final String DESCRIPCION = "Bad Request (400)";

    public BadRequestException(String mensaje){
        super(DESCRIPCION + ". "+mensaje);
    }
}