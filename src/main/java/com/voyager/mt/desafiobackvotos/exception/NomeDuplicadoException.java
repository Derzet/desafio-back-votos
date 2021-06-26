package com.voyager.mt.desafiobackvotos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NomeDuplicadoException extends ResponseStatusException {
    public NomeDuplicadoException(String nome) {
        super(HttpStatus.CONFLICT, "Nome duplicado "+ nome);
    }
}
