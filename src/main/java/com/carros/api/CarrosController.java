package com.carros.api;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import com.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/carros")
public class CarrosController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public ResponseEntity<List<CarroDTO>> getCarros(){
        return ResponseEntity.ok(carroService.getCarros());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCarroById(@PathVariable("id") Long id){
        Optional<Carro> carro = carroService.getCarroById(id);

        return carro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

//        return carro.map(c -> ResponseEntity.ok(c))
//                .orElse(ResponseEntity.notFound().build());

//        return carro.isPresent() ?
//                ResponseEntity.ok(carro.get()) :
//                ResponseEntity.notFound().build();
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CarroDTO>> getCarrosByTipo(@PathVariable("tipo") String tipo){
        List<Carro> carros = carroService.getCarroByTipo(tipo);

        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @PostMapping
    public Carro salvarCarro(@RequestBody Carro carro){
        Carro c = carroService.save(carro);

        return c;
    }

    @PutMapping("/{id}")
    public String editarCarro(@PathVariable("id") Long id, @RequestBody Carro carro){
        Carro c = carroService.update(carro, id);

        return "Atualizado";
    }

    @DeleteMapping("/{id}")
    public String excluirCarro(@PathVariable("id") Long id){
        carroService.delete(id);

        return "Excluido";
    }





}
