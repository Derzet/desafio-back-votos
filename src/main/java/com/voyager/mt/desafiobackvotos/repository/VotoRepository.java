package com.voyager.mt.desafiobackvotos.repository;

import com.voyager.mt.desafiobackvotos.model.entity.Voto;
import com.voyager.mt.desafiobackvotos.model.entity.VotoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface VotoRepository extends JpaRepository<Voto, VotoPK> {

    @Query("SELECT v.decissao, count(*) from Voto v" +
            " where v.votoPK.pautaId = :idPauta group by v.decissao")
   Map<Character,Long> contaVotosPorTipo(@Param("idPauta") Long idPauta);

}