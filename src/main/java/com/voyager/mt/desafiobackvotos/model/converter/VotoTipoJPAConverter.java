package com.voyager.mt.desafiobackvotos.model.converter;

import com.voyager.mt.desafiobackvotos.model.enume.VotoTipo;

import javax.persistence.AttributeConverter;

public class VotoTipoJPAConverter implements AttributeConverter<VotoTipo,Character> {

    @Override
    public Character convertToDatabaseColumn(VotoTipo votoTipo) {
        return votoTipo.getCode();
    }

    @Override
    public VotoTipo convertToEntityAttribute(Character votoTipo) {
        return VotoTipo.pegaTipo(votoTipo);
    }
}
