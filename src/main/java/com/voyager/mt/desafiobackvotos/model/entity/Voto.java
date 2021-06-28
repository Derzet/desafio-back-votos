package com.voyager.mt.desafiobackvotos.model.entity;

import com.voyager.mt.desafiobackvotos.model.converter.VotoTipoJPAConverter;
import com.voyager.mt.desafiobackvotos.model.enume.VotoTipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Convert(converter = VotoTipoJPAConverter.class)
    private VotoTipo tipo;

    @NotNull
    private LocalDateTime dataEvento;

    @CreatedDate
    private LocalDate dataCriacao;

}
