package com.voyager.mt.desafiobackvotos.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voto implements Serializable {

    @EmbeddedId
    private VotoPK votoPK;

    private Character decissao;

    private LocalDateTime dataEvento;

    @CreatedDate
    private LocalDate dataCriacao;

}
