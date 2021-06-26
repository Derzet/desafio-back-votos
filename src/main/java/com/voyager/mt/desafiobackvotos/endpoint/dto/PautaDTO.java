package com.voyager.mt.desafiobackvotos.endpoint.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PautaDTO {

    private Long id;

    @NotNull
    private String nome;

    private String descricao;

}

