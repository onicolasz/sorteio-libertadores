package com.nicolasbarros.sorteiolibertadores.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() { super("Resource não encontrado"); }
    public ResourceNotFoundException(String message) { super(message); }

}
