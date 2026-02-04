package com.spring_payments_test.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="pagamentos")
@Table
@Builder
public class Pagamentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JoinColumn(name = "Pagador_id")
    @ManyToOne
    private Usuario Pagador;
    private LocalDateTime dataHoraDoPagamento;
    @PrePersist
    void prePersist(){
        dataHoraDoPagamento = LocalDateTime.now();
    }

    private BigDecimal valor;
    @JoinColumn(name = "recebedor_id")
    @ManyToOne
    private Usuario Recebedor;



}