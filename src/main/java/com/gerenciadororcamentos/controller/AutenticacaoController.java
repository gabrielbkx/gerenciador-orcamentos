package com.gerenciadororcamentos.controller;


import com.gerenciadororcamentos.dto.DadosLogin;
import com.gerenciadororcamentos.dto.DadosRegistroUsuario;
import com.gerenciadororcamentos.entity.Usuario;
import com.gerenciadororcamentos.repository.UsuarioRepository;
import com.gerenciadororcamentos.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/cadastro")
    public ResponseEntity<?> registrar(@Valid @RequestBody DadosRegistroUsuario dados) {

        if (usuarioRepository.existsByUsername(dados.username())) {
            return ResponseEntity.badRequest().body("Username já está em uso");
        }

        Usuario novoUsuario = new Usuario(
                dados.username(),
                passwordEncoder.encode(dados.password()),
                dados.role() != null ? dados.role() : null // já cai no default USER da entidade
        );

        usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok("Usuário criado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid DadosLogin dados) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dados.username(), dados.password())
        );

        String token = jwtService.gerarToken(
                (org.springframework.security.core.userdetails.UserDetails) auth.getPrincipal()
        );

        return ResponseEntity.ok(token);
    }

}
