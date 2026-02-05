package com.spring_payments_test.infrastructure.repository;

import com.spring_payments_test.infrastructure.entity.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
}
