package com.voyager.mt.desafiobackvotos.model.entity;

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

    private Long votosSim;

    private Long votosNao;

    private String resultado;

    @CreatedDate
    private LocalDate dataCriacao;


}
