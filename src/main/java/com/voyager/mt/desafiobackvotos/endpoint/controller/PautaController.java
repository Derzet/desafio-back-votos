package com.voyager.mt.desafiobackvotos.endpoint.controller;

import com.voyager.mt.desafiobackvotos.endpoint.dto.PautaDTO;
import com.voyager.mt.desafiobackvotos.endpoint.dto.ResultadoDTO;
import com.voyager.mt.desafiobackvotos.endpoint.dto.VotoDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface PautaController {

    @ResponseBody
    @PostMapping
    @ApiOperation(value = "salva uma pauta", response = PautaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso", response = PautaDTO.class),
            @ApiResponse(code = 400, message = "Entrada inválida", response = Void.class),
            @ApiResponse(code = 409, message = "Conflito", response = Void.class)
    })
    PautaDTO criaPauta(@Valid @RequestBody PautaDTO pautaDTO);

    @ApiOperation(value = "Abre uma sessao para pauta", response = PautaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso", response = PautaDTO.class),
            @ApiResponse(code = 400, message = "Entrada inválida", response = Void.class),
            @ApiResponse(code = 404, message = "Pauta nao encontrada", response = Void.class)
    })
    @PutMapping(path = "/{idPauta}/abertura")
    PautaDTO abreSessaoPauta(@RequestHeader(name = "Data-expiracao-sessao", required = false) String dataExpiracaoSessao,
                             @PathVariable Long idPauta);

    @ApiOperation(value = "Vota em pauta", response = VotoDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso", response = VotoDTO.class),
            @ApiResponse(code = 400, message = "Entrada inválida", response = Void.class),
            @ApiResponse(code = 404, message = "Nao encontrado", response = Void.class),
            @ApiResponse(code = 412, message = "Voto inválido! ", response = Void.class)
    })
    @PostMapping(path = "/voto")
    VotoDTO votaPauta(@Valid @RequestBody VotoDTO votoDTO);

    @ApiOperation(value = "Obtem resultado pauta calculado através dos votos", response = ResultadoDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso", response = ResultadoDTO.class),
            @ApiResponse(code = 404, message = "Nao encontrado", response = Void.class),
    })
    @PostMapping(path = "/{idPauta}/resultado")
    ResultadoDTO resultadoPauta(@PathVariable Long idPauta);
}
