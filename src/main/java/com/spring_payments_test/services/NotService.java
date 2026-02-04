package com.spring_payments_test.services;

import com.spring_payments_test.infrastructure.clients.AuthClient;
import com.spring_payments_test.infrastructure.clients.NotClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NotService {
    private final NotClient client;

    public void enviarNotficacao(){
        client.enviarNotificacao();
    }
}
