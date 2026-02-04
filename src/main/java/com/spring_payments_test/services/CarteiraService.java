package com.spring_payments_test.services;

import com.spring_payments_test.infrastructure.entity.Carteira;
import com.spring_payments_test.infrastructure.repository.CarteiraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarteiraService {

    private final CarteiraRepository repository;

    public void salvar(Carteira carteira){
        repository.save(carteira);

    }
}
