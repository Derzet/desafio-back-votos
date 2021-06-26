package com.voyager.mt.desafiobackvotos.endpoint.dto;

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

    private Character decissao;

    @NotNull
    private String dataEvento;
}
