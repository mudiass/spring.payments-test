package com.spring_payments_test.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="usuario")
@Table
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpfCnpj;
    @Column(unique = true, nullable = false)
    private String email;

    private String nomeCompleto;
    @Column(unique = true, nullable = false)
    private String senha;


    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Carteira carteira;

    private Usuarios usuarios;


}
