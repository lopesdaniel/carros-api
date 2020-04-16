package com.carros.api;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/carros")
public class CarrosController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public Iterable<Carro> getCarros(){
        return carroService.getCarros();
    }

    @GetMapping("/{id}")
    public Optional<Carro> getCarroById(@PathVariable("id") Long id){
        return carroService.getCarroById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public Iterable<Carro> getCarrosByTipo(@PathVariable("tipo") String tipo){
        return carroService.getCarroByTipo(tipo);
    }

    





}
