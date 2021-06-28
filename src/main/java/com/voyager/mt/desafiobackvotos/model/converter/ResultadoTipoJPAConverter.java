package com.voyager.mt.desafiobackvotos.model.converter;

import com.voyager.mt.desafiobackvotos.model.enume.ResultadoTipo;

import javax.persistence.AttributeConverter;

public class ResultadoTipoJPAConverter implements AttributeConverter<ResultadoTipo,Character> {

    @Override
    public Character convertToDatabaseColumn(ResultadoTipo resultadoTipo) {
        return resultadoTipo.getCode();
    }

    @Override
    public ResultadoTipo convertToEntityAttribute(Character resultadoTipo) {
        return ResultadoTipo.pegaTipo(resultadoTipo);
    }
}
