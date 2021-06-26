package com.voyager.mt.desafiobackvotos.service.impl;

import com.voyager.mt.desafiobackvotos.exception.*;
import com.voyager.mt.desafiobackvotos.model.entity.Pauta;
import com.voyager.mt.desafiobackvotos.model.entity.Resultado;
import com.voyager.mt.desafiobackvotos.model.entity.Voto;
import com.voyager.mt.desafiobackvotos.repository.AssociadoRepository;
import com.voyager.mt.desafiobackvotos.repository.PautaRepository;
import com.voyager.mt.desafiobackvotos.repository.ResultadoRepository;
import com.voyager.mt.desafiobackvotos.repository.VotoRepository;
import com.voyager.mt.desafiobackvotos.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

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
        LocalDateTime dataExpiracaoSessaoF =
                (dataExpiracaoSessao != null) ? converteStringParaLocalDateTime(dataExpiracaoSessao) : LocalDateTime.now().plusMinutes(1);
        pauta.setDataExpiracaoSessao(dataExpiracaoSessaoF);
        return pautaRepository.save(pauta);
    }

    public Voto votaEmPauta(Voto voto) {
        if (!associadoRepository.existsById(voto.getVotoPK().getAssociadoId())) {
            throw new AssociadoNaoEncontradoException();
        }
        LocalDateTime dataExpiracaoSessao = pautaRepository.findById(voto.getVotoPK().getAssociadoId())
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

    public Resultado obtemResultadoPauta(Long idPauta) {
        pautaRepository.findById(idPauta).orElseThrow(PautaNaoEncontradaException::new);
        Map<Character, Long> mapper = votoRepository.contaVotosPorTipo(idPauta);

        Long quantidadeVotosNao = ((mapper.get('S') == null)? 0L : mapper.get('S'));
        Long quantidadeVotosSim = ((mapper.get('N') == null)? 0L : mapper.get('N'));
        Resultado resultado = Resultado.builder().pauta(Pauta.builder().id(idPauta).build())
                .votosNao(quantidadeVotosNao)
                .votosSim(quantidadeVotosSim)
                .resultado(validaResultado(quantidadeVotosNao, quantidadeVotosSim)).build();

        return resultadoRepository.save(resultado);
    }

    private LocalDateTime converteStringParaLocalDateTime(String dataExpiracaoSessao) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:HH dd-MM-yyyy");
        return LocalDateTime.parse(dataExpiracaoSessao, formatter);
    }

    private String validaResultado(Long quantidadeVotosNao, Long quantidadeVotosSim) {
        if (quantidadeVotosNao == quantidadeVotosSim) {
            return "EMPATADO";
        }
        if (quantidadeVotosNao < quantidadeVotosSim) {
            return "APROVADO";
        } else {
            return "REJEITADA";
        }
    }
}
