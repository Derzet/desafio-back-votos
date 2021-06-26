package com.voyager.mt.desafiobackvotos.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoPK implements Serializable {

    @JoinColumn(name = "ID_ASSOCIADO")
    private Long associadoId;

    @JoinColumn(name = "ID_PAUTA")
    private Long pautaId;
}
