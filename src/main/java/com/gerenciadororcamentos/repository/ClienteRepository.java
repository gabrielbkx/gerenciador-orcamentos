package com.gerenciadororcamentos.repository;

import com.gerenciadororcamentos.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Page<Cliente> findAllByNome(Pageable paginacao);
    boolean existsByEmail(String email);

}
