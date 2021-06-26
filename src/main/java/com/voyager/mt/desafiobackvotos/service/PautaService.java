package com.voyager.mt.desafiobackvotos.service;

import com.voyager.mt.desafiobackvotos.model.entity.Pauta;
import com.voyager.mt.desafiobackvotos.model.entity.Resultado;
import com.voyager.mt.desafiobackvotos.model.entity.Voto;

public interface PautaService {

    Pauta criaPauta(final Pauta pauta);

    Voto votaEmPauta(final Voto voto);

    Pauta abreSessaoPauta(final Long idPauta,final String dataExpiracaoSessao);

    Resultado obtemResultadoPauta(final Long idPauta);
}
