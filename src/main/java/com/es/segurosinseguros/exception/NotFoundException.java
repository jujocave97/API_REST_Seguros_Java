package com.es.segurosinseguros.exception;

public class NotFoundException extends RuntimeException {
  public static final String DESCRIPCION = "Not Found (404)";
  public NotFoundException(String mensaje){
    super(DESCRIPCION + ". "+mensaje);
  }
}
