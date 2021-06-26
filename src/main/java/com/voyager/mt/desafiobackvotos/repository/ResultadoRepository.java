package com.voyager.mt.desafiobackvotos.repository;

import com.voyager.mt.desafiobackvotos.model.entity.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long> {
}
