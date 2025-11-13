package com.johnny.agendadortarefas.infrastructure.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Para JWT stateless não precisamos consultar nada aqui.
        // Apenas devolvemos um principal com o username do token.
        return User.builder()
                .username(email)
                .password("")          // não é usado após o login JWT
                .authorities("USER")   // ajuste as roles quando precisar
                .build();
    }
}
