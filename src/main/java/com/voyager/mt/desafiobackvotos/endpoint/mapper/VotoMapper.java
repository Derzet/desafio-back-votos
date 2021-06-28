package com.voyager.mt.desafiobackvotos.endpoint.mapper;

import com.voyager.mt.desafiobackvotos.endpoint.dto.VotoDTO;
import com.voyager.mt.desafiobackvotos.model.entity.Voto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VotoMapper {

    @Mapping(source = "associadoId", target = "votoPK.associadoId")
    @Mapping(source = "pautaId", target = "votoPK.pautaId")
    @Mapping(target = "dataEvento", dateFormat = "HH:mm:ss dd-MM-yyyy")
    Voto dTOparaEntity(VotoDTO votoDTO);

    @Mapping(source = "votoPK.associadoId", target = "associadoId")
    @Mapping(source = "votoPK.pautaId", target = "pautaId")
    VotoDTO entityparaDTO(Voto voto);
}
