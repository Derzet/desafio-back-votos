package com.voyager.mt.desafiobackvotos.endpoint.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResultadoDTO {

    private Long votosSim;

    private Long votosNao;

    private String resultado;
}
