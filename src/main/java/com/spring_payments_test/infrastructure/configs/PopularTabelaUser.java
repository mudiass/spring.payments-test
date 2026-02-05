package com.spring_payments_test.infrastructure.configs;

import com.spring_payments_test.infrastructure.entity.Carteira;
import com.spring_payments_test.infrastructure.entity.Usuario;
import com.spring_payments_test.infrastructure.entity.Usuarios;
import com.spring_payments_test.infrastructure.repository.CarteiraRepository;
import com.spring_payments_test.infrastructure.repository.UsuarioRepository;
import org.apache.el.lang.FunctionMapperImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class PopularTabelaUser {
    @Bean
    CommandLineRunner popularBanco(UsuarioRepository usuarioRepository, CarteiraRepository carteiraRepository) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                Usuario usuario1 = new Usuario(null, "111111111", "carlos@email.com", "Carlos",
                        encoder.encode("123456"), null, Usuarios.Comum);

                Usuario usuario2 = new Usuario(null, "22222222222", "ana@email.com", "222222222222",
                        encoder.encode("123456"), null, Usuarios.Comum);

                Usuario funcionario = new Usuario(null, "33333333333", "loja@email.com", "exemplo loja",
                        encoder.encode("123456"), null, Usuarios.Funcionario);

                usuarioRepository.saveAll(List.of(usuario1, usuario2, funcionario));


                Carteira carteira1 = new Carteira(null, new BigDecimal("1000.00"), usuario1);
                Carteira carteira2 = new Carteira(null, new BigDecimal("2000.00"), usuario2);
                Carteira carteira3 = new Carteira(null, new BigDecimal("5000.00"), funcionario);

                carteiraRepository.saveAll(List.of(carteira1, carteira2, carteira3));

                System.out.println("Usuários e carteiras populados com sucesso!");
            }
        };
    }
}
