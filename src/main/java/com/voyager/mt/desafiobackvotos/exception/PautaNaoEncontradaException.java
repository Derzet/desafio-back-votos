package com.voyager.mt.desafiobackvotos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PautaNaoEncontradaException extends ResponseStatusException {
    public PautaNaoEncontradaException() {
        super(HttpStatus.NOT_FOUND, "Pauta nao encontrada");
    }
}
