package com.carros.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    public Iterable<Carro> getCarros(){
        return repository.findAll();
    }


    public Optional<Carro> getCarroById(Long id) {
        return repository.findById(id);
    }

    public Iterable<Carro> getCarroByTipo(String tipo) {
        return repository.findByTipo(tipo);
    }
}
