package com.carros.domain;

import com.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    public List<CarroDTO> getCarros(){
        List<Carro> carros = repository.findAll();

//        List<CarroDTO> lista = new ArrayList<>();
//        for(Carro c : carros){
//            lista.add(new CarroDTO(c));
//        }

        return carros.stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList());


//        return repository.findAll().stream().map(c -> new CarroDTO(c)).collect(Collectors.toList());
    }


    public Optional<CarroDTO> getCarroById(Long id) {
        return repository.findById(id).map(CarroDTO::create);
    }

    public List<CarroDTO> getCarroByTipo(String tipo) {
        return repository.findByTipo(tipo).stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList());
    }

//    public CarroDTO save(Carro carro) {
//        Assert.isNull(carro.getId(),"Não foi possível inserir o registro");
//
//        return CarroDTO.create(repository.save(carro));
//    }

    public Carro save(Carro carro){
        return repository.save(carro);
    }

    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");

        Optional<Carro> optional = repository.findById(id);
        if(optional.isPresent()){
            Carro carBd = optional.get();
            carBd.setNome(carro.getNome());
            carBd.setTipo(carro.getTipo());

            repository.save(carBd);

            return CarroDTO.create(carBd);
        }else{
            throw new RuntimeException("Não foi possível atualizar o registro");
        }

    }

    public void delete(Long id) {
        if(getCarroById(id).isPresent()){
            repository.deleteById(id);
        }
    }
}
