package com.voyager.mt.desafiobackvotos.repository;

import com.voyager.mt.desafiobackvotos.model.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

    Optional<Pauta> findByNome(String nome);

}
