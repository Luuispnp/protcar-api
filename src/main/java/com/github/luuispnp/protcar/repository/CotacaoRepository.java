package com.github.luuispnp.protcar.repository;

import com.github.luuispnp.protcar.entity.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {
}
