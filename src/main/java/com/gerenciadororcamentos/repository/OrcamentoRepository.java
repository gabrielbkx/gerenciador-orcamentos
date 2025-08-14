package com.gerenciadororcamentos.repository;

import com.gerenciadororcamentos.entity.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
}
