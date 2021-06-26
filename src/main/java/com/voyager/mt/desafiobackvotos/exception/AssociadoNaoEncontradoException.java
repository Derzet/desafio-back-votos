package com.voyager.mt.desafiobackvotos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AssociadoNaoEncontradoException extends ResponseStatusException {
    public AssociadoNaoEncontradoException() {
        super(HttpStatus.NOT_FOUND, "Associado nao encontrado");
    }
}
