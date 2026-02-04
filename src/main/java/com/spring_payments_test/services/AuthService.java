package com.spring_payments_test.services;

import com.spring_payments_test.infrastructure.clients.AuthClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthClient client;

    public boolean validarPagamento(){
        if (Objects.equals(client.validarAutorizacao().data().authorization(), "true")){
            return true;
        }
        return false;
    }
}
