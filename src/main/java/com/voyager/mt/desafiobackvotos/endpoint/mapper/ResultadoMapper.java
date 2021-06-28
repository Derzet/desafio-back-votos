package com.voyager.mt.desafiobackvotos.endpoint.mapper;

import com.voyager.mt.desafiobackvotos.endpoint.dto.ResultadoDTO;
import com.voyager.mt.desafiobackvotos.model.entity.Resultado;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResultadoMapper {

    ResultadoDTO entityparaDTO(Resultado resultado);

}
