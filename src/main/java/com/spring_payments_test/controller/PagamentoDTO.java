package com.spring_payments_test.controller;

import java.math.BigDecimal;

public record PagamentoDTO(BigDecimal value, Long payer, Long payee ) {
}
