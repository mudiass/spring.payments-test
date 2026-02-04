package com.spring_payments_test.services;

import com.spring_payments_test.exceptions.UserNotFound;
import com.spring_payments_test.infrastructure.entity.Usuario;
import com.spring_payments_test.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public Usuario buscarUsuario(long id){
        return repository.findById(id)
                .orElseThrow(
                        () -> new UserNotFound("Usuário não encontrado"));
    }
}
