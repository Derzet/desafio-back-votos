package com.voyager.mt.desafiobackvotos.repository;

import com.voyager.mt.desafiobackvotos.model.entity.Voto;
import com.voyager.mt.desafiobackvotos.model.entity.VotoPK;
import com.voyager.mt.desafiobackvotos.repository.custom.group.VotoGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, VotoPK> {

    @Query("SELECT new com.voyager.mt.desafiobackvotos.repository.custom.group.VotoGroup(v.tipo, count(v)) from Voto v" +
            " where v.votoPK.pautaId = :idPauta group by v.tipo")
    List<VotoGroup> contaVotosPorTipo(@Param("idPauta") Long idPauta);

}