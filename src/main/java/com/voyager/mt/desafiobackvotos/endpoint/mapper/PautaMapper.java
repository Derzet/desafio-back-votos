package com.voyager.mt.desafiobackvotos.endpoint.mapper;

import com.voyager.mt.desafiobackvotos.endpoint.dto.PautaDTO;
import com.voyager.mt.desafiobackvotos.model.entity.Pauta;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PautaMapper {

    Pauta dTOparaEntity(PautaDTO pautaDTO);

    PautaDTO entityparaDTO(Pauta pauta);
}
