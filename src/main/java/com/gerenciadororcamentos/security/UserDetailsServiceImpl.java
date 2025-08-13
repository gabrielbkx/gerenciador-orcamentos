package com.gerenciadororcamentos.security;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Aqui você poderia buscar do banco de dados
        if (username.equals("admin")) {
            return new User("admin", "{noop}admin123", Collections.emptyList());
        }
        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}
