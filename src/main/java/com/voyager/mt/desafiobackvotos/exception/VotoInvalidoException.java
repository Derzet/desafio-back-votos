package com.voyager.mt.desafiobackvotos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VotoInvalidoException extends ResponseStatusException {
    public VotoInvalidoException() {
        super(HttpStatus.CONFLICT, "Voto não é valido! data ultrapassada.");
    }
}
