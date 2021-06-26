package com.voyager.mt.desafiobackvotos.exception;

import com.voyager.mt.desafiobackvotos.model.entity.Voto;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VotoDuplicadoException extends ResponseStatusException {
    public VotoDuplicadoException(Voto voto) {
        super(HttpStatus.NOT_FOUND, "Voto duplicado para usuário "+voto.getVotoPK().getAssociadoId()+
                " pauta" + voto.getVotoPK().getPautaId());
    }
}
