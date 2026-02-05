package com.spring_payments_test.infrastructure.repository;

import com.spring_payments_test.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
