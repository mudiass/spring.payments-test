package com.spring_payments_test.infrastructure.clients;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url ="https://util.devi.tools/api/v1/notify" , name = "notificação")
public interface NotClient {

    @PostMapping
    void enviarNotificacao();

}
