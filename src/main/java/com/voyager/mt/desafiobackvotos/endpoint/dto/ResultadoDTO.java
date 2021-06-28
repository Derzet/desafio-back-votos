package com.voyager.mt.desafiobackvotos.endpoint.dto;

import com.voyager.mt.desafiobackvotos.model.enume.ResultadoTipo;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResultadoDTO {

    private Long numeroVotosSim;

    private Long numeroVotosNao;

    private ResultadoTipo tipo;
}
