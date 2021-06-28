package com.voyager.mt.desafiobackvotos.endpoint.dto;

import com.voyager.mt.desafiobackvotos.model.enume.VotoTipo;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VotoDTO {

    private Long associadoId;

    private Long pautaId;

    private VotoTipo tipo;

    @NotNull
    private String dataEvento;
}
