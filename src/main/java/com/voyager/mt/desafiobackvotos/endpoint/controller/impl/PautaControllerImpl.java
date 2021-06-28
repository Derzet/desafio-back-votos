package com.voyager.mt.desafiobackvotos.endpoint.controller.impl;

import com.voyager.mt.desafiobackvotos.endpoint.controller.PautaController;
import com.voyager.mt.desafiobackvotos.endpoint.dto.PautaDTO;
import com.voyager.mt.desafiobackvotos.endpoint.dto.ResultadoDTO;
import com.voyager.mt.desafiobackvotos.endpoint.dto.VotoDTO;
import com.voyager.mt.desafiobackvotos.endpoint.mapper.PautaMapper;
import com.voyager.mt.desafiobackvotos.endpoint.mapper.ResultadoMapper;
import com.voyager.mt.desafiobackvotos.endpoint.mapper.VotoMapper;
import com.voyager.mt.desafiobackvotos.model.entity.Pauta;
import com.voyager.mt.desafiobackvotos.model.entity.Voto;
import com.voyager.mt.desafiobackvotos.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/pauta")
@ResponseStatus(HttpStatus.OK)
public class PautaControllerImpl implements PautaController {

    @Autowired
    private PautaService pautaService;

    @Autowired
    private PautaMapper pautaMapper;

    @Autowired
    private VotoMapper votoMapper;

    @Autowired
    private ResultadoMapper resultadoMapper;

    @Override
    @ResponseBody
    @PostMapping
    public PautaDTO criaPauta(@Valid @RequestBody PautaDTO pautaDTO) {
        Pauta pauta = pautaMapper.dTOparaEntity(pautaDTO);
        return pautaMapper.entityparaDTO(pautaService.criaPauta(pauta));
    }

    @Override
    @PutMapping(path = "/{idPauta}/abertura")
    public PautaDTO abreSessaoPauta(@RequestHeader(name = "Data-expiracao-sessao", required = false) String dataExpiracaoSessao,
                                    @PathVariable Long idPauta) {
        return pautaMapper.entityparaDTO(pautaService.abreSessaoPauta(idPauta, dataExpiracaoSessao));
    }

    @PostMapping(path = "/voto")
    public VotoDTO votaPauta(@Valid @RequestBody VotoDTO votoDTO) {
        Voto voto = votoMapper.dTOparaEntity(votoDTO);
        return votoMapper.entityparaDTO(pautaService.votaEmPauta(voto));
    }

    @Override
    @PostMapping(path = "/{idPauta}/resultado")
    public ResultadoDTO resultadoPauta(@PathVariable Long idPauta) {
        return resultadoMapper.entityparaDTO(pautaService.obtemResultadoPauta(idPauta));
    }

}
