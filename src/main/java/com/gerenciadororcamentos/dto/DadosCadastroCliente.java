package com.gerenciadororcamentos.dto;

import com.gerenciadororcamentos.entity.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroCliente(
        @NotBlank
        String nome,
        @NotNull
        @Email
        String email,
        @NotNull
        @Pattern(regexp = "^(\\\\+55\\\\s?)?(\\\\(?\\\\d{2}\\\\)?\\\\s?)?\\\\d{4,5}-?\\\\d{4}$")
        String telefone) {

        public DadosCadastroCliente(Cliente cliente) {
                this(cliente.getNome(), cliente.getEmail(), cliente.getTelefone());
        }


}
