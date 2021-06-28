package com.voyager.mt.desafiobackvotos.repository.custom.group;

import com.voyager.mt.desafiobackvotos.model.enume.VotoTipo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotoGroup {
    private VotoTipo tipo;
    private Long total;

    public VotoGroup(VotoTipo tipo, Long total) {
        this.tipo = tipo;
        this.total = total;
    }
}
