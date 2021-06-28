package com.voyager.mt.desafiobackvotos.model.enume;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum VotoTipo {
    SIM('S'),
    NAO('N');


    private Character code;
    private static final Map<Character, VotoTipo> ENUM_MAPEAMENTO;


    VotoTipo(Character code) {
        this.code = code;
    }

    public Character getCode() {
        return this.code;
    }

    static {
        Map<Character, VotoTipo> mapa = new HashMap<>();
        for (VotoTipo instance : VotoTipo.values()) {
            mapa.put(instance.getCode(), instance);
        }
        ENUM_MAPEAMENTO = Collections.unmodifiableMap(mapa);
    }

    public static VotoTipo pegaTipo(Character code) {
        if (ENUM_MAPEAMENTO.get(Character.toUpperCase(code)) == null) {
            throw new RuntimeException(String.format("Não existe implementado o tipo voto para o (%s)", code));
        }
        return ENUM_MAPEAMENTO.get(code);
    }
}
