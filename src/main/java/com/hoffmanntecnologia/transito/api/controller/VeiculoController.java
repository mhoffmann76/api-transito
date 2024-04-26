package com.hoffmanntecnologia.transito.api.controller;

import com.hoffmanntecnologia.transito.domain.model.Veiculo;
import com.hoffmanntecnologia.transito.domain.repository.VeiculoReporitory;
import com.hoffmanntecnologia.transito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")


public class VeiculoController {
    private final VeiculoReporitory veiculoReporitory;
    private final RegistroVeiculoService registroVeiculoService;
    @GetMapping
    public List<Veiculo> listar(){
        return veiculoReporitory.findAll();

    }

    @GetMapping("/{id}")

    public ResponseEntity<Veiculo> buscar(@PathVariable Long id){
        return veiculoReporitory.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastrar(@Valid @RequestBody Veiculo veiculo){
        return registroVeiculoService.cadastrar(veiculo);


    }




}
