package com.hoffmanntecnologia.transito.api.controller;

import com.hoffmanntecnologia.transito.api.assembler.VeiculoAssembler;
import com.hoffmanntecnologia.transito.api.model.VeiculoModel;
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
    private final VeiculoAssembler veiculoAssembler;
    @GetMapping
    public List<VeiculoModel> listar(){
        return veiculoAssembler.toCollectionModel(veiculoReporitory.findAll());



    }

    @GetMapping("/{id}")

    public ResponseEntity<VeiculoModel> buscar(@PathVariable Long id){
        return veiculoReporitory.findById(id)
                .map(veiculoAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoModel cadastrar(@Valid @RequestBody Veiculo veiculo){
        return veiculoAssembler.toModel(registroVeiculoService.cadastrar(veiculo));

    }




}
