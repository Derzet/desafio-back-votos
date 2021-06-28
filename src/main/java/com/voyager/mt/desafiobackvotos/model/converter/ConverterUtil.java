package com.voyager.mt.desafiobackvotos.model.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ConverterUtil {

    private ConverterUtil(){}

    public static LocalDateTime converteStringParaLocalDateTime(final String dataExpiracaoSessao) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:HH dd-MM-yyyy");
        return LocalDateTime.parse(dataExpiracaoSessao, formatter);
    }
}
