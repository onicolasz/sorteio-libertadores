package com.nicolasbarros.sorteiolibertadores.exceptions;

public class TeamNotDrawnNotFoundException extends RuntimeException {
    public TeamNotDrawnNotFoundException() { super("Não existem mais times para serem sorteados"); }
    public TeamNotDrawnNotFoundException(String message) { super(message); }

}
