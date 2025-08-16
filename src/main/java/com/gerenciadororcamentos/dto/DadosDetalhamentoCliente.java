package com.gerenciadororcamentos.dto;

import com.gerenciadororcamentos.entity.Cliente;
import jakarta.validation.constraints.NotNull;


public record DadosDetalhamentoCliente(

        @NotNull(message = "O ID do cliente é obrigatório")
        Long id,
        String nome,
        String email,
        String telefone) {


    public DadosDetalhamentoCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone());
    }
}
