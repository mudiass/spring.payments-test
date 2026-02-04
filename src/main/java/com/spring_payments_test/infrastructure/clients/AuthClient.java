package com.spring_payments_test.infrastructure.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url ="https://util.devi.tools/api/v2/authorize" , name = "autorização")
public interface AuthClient {

    @GetMapping
    AuthDTO validarAutorizacao();

}
