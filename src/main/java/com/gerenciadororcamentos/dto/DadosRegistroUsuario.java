package com.gerenciadororcamentos.dto;


import com.gerenciadororcamentos.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosRegistroUsuario(
        @NotBlank @Size(min=3, max=80) String username,
        @NotBlank @Size(min=6, max=100) String password,
        Role role
) {
    public DadosRegistroUsuario(String username, String password) {
        this(username, password, Role.USER);
    }
}
