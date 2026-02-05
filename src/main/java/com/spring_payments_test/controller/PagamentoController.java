package com.spring_payments_test.controller;

import com.spring_payments_test.services.PagamentosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PagamentoController {

    private final PagamentosService pagamentosService;

    @PostMapping
    public ResponseEntity realizarPagamento(@RequestBody PagamentoDTO pagamentoDTO){
        pagamentosService.enviarValores(pagamentoDTO);
        return ResponseEntity.accepted().build();
    }

}
