package com.spring_payments_test.infrastructure.repository;


import com.spring_payments_test.infrastructure.entity.Pagamentos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamentos, long> {
}
