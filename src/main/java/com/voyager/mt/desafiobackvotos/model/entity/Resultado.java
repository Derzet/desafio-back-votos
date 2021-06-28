package com.voyager.mt.desafiobackvotos.model.entity;

import com.voyager.mt.desafiobackvotos.model.converter.ResultadoTipoJPAConverter;
import com.voyager.mt.desafiobackvotos.model.enume.ResultadoTipo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@EntityListeners(EntityListeners.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "id_pauta")
    @ManyToOne
    private Pauta pauta;

    private Long numeroVotosSim;

    private Long numeroVotosNao;

    @Convert(converter = ResultadoTipoJPAConverter.class)
    private ResultadoTipo tipo;

    @CreatedDate
    private LocalDate dataCriacao;


}
