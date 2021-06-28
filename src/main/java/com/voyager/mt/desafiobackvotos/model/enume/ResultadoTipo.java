package com.voyager.mt.desafiobackvotos.model.enume;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ResultadoTipo {


    EMPATADO('E'),
    APROVADO('A'),
    REJEITADO('R');

    private Character code;
    private static final Map<Character, ResultadoTipo> ENUM_MAPEAMENTO;


    ResultadoTipo (Character code) {
        this.code = code;
    }

    public Character getCode() {
        return this.code;
    }

    static {
        Map<Character,ResultadoTipo> mapa = new HashMap<>();
        for (ResultadoTipo instance : ResultadoTipo.values()) {
            mapa.put(instance.getCode(),instance);
        }
        ENUM_MAPEAMENTO = Collections.unmodifiableMap(mapa);
    }

    public static ResultadoTipo pegaTipo(Character code) {
        if (ENUM_MAPEAMENTO.get(Character.toUpperCase(code)) == null) {
            throw new RuntimeException(String.format("Não existe implementado o tipo resultado para o (%s)", code));
        }
        return ENUM_MAPEAMENTO.get(code);
    }

}
