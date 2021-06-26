package com.voyager.mt.desafiobackvotos.repository;

import com.voyager.mt.desafiobackvotos.model.entity.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {
}
