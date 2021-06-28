package com.voyager.mt.desafiobackvotos.service.impl;

import com.voyager.mt.desafiobackvotos.exception.*;
import com.voyager.mt.desafiobackvotos.model.converter.ConverterUtil;
import com.voyager.mt.desafiobackvotos.model.entity.Pauta;
import com.voyager.mt.desafiobackvotos.model.entity.Resultado;
import com.voyager.mt.desafiobackvotos.model.entity.Voto;
import com.voyager.mt.desafiobackvotos.model.enume.ResultadoTipo;
import com.voyager.mt.desafiobackvotos.model.enume.VotoTipo;
import com.voyager.mt.desafiobackvotos.repository.AssociadoRepository;
import com.voyager.mt.desafiobackvotos.repository.PautaRepository;
import com.voyager.mt.desafiobackvotos.repository.ResultadoRepository;
import com.voyager.mt.desafiobackvotos.repository.VotoRepository;
import com.voyager.mt.desafiobackvotos.repository.custom.group.VotoGroup;
import com.voyager.mt.desafiobackvotos.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PautaServiceImpl implements PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private ResultadoRepository resultadoRepository;

    @Autowired
    private AssociadoRepository associadoRepository;

    public Pauta criaPauta(final Pauta pauta) {
        if (pautaRepository.findByNome(pauta.getNome()).isPresent()) {
            throw new NomeDuplicadoException(pauta.getNome());
        } else {
            return pautaRepository.save(pauta);
        }
    }

    public Pauta abreSessaoPauta(final Long idPauta, final String dataExpiracaoSessao) {
        Pauta pauta = pautaRepository.findById(idPauta).orElseThrow(PautaNaoEncontradaException::new);
        LocalDateTime dataExpiracaoSessaoFinal =
                (dataExpiracaoSessao != null) ? ConverterUtil.converteStringParaLocalDateTime(dataExpiracaoSessao) : LocalDateTime.now().plusMinutes(1);
        pauta.setDataExpiracaoSessao(dataExpiracaoSessaoFinal);
        return pautaRepository.save(pauta);
    }

    public Voto votaEmPauta(final Voto voto) {
        if (!associadoRepository.existsById(voto.getVotoPK().getAssociadoId())) {
            throw new AssociadoNaoEncontradoException();
        }
        LocalDateTime dataExpiracaoSessao = pautaRepository.findById(voto.getVotoPK().getPautaId())
                .orElseThrow(PautaNaoEncontradaException::new).getDataExpiracaoSessao();

        votoRepository.findById(voto.getVotoPK()).ifPresent(votoDuplicado ->
        {
            throw new VotoDuplicadoException(votoDuplicado);
        });

        if (dataExpiracaoSessao != null && dataExpiracaoSessao.isAfter(voto.getDataEvento())) {
            return votoRepository.save(voto);
        } else {
            throw new VotoInvalidoException();
        }
    }

    public Resultado obtemResultadoPauta(final Long idPauta) {
        pautaRepository.findById(idPauta).orElseThrow(PautaNaoEncontradaException::new);
        List<VotoGroup> listaVotoGroup = votoRepository.contaVotosPorTipo(idPauta);
        Long numeroVotosNao = calculaVotosPorTipo(listaVotoGroup, VotoTipo.NAO);
        Long numeroVotosSim = calculaVotosPorTipo(listaVotoGroup, VotoTipo.SIM);
        Resultado resultado = Resultado.builder().pauta(Pauta.builder().id(idPauta).build())
                .numeroVotosNao(numeroVotosNao)
                .numeroVotosSim(numeroVotosSim)
                .tipo(calculaResultado(numeroVotosNao, numeroVotosSim)).build();

        return resultadoRepository.save(resultado);
    }

    private ResultadoTipo calculaResultado(final Long quantidadeVotosNao, final Long quantidadeVotosSim) {
        if (quantidadeVotosNao.equals(quantidadeVotosSim)) {
            return ResultadoTipo.EMPATADO;
        }
        if (quantidadeVotosNao < quantidadeVotosSim) {
            return ResultadoTipo.APROVADO;
        } else {
            return ResultadoTipo.REJEITADO;
        }
    }

    private Long calculaVotosPorTipo(List<VotoGroup> listaVotoGroup, VotoTipo votoTipo) {
        return listaVotoGroup.stream().parallel()
                .filter(v -> v.getTipo() == votoTipo)
                .map(v -> v.getTotal()).findFirst().orElse(0L);
    }


}
