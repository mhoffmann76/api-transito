package com.hoffmanntecnologia.transito.api.controller;

import com.hoffmanntecnologia.transito.domain.model.Proprietario;
import com.hoffmanntecnologia.transito.domain.repository.ProprietarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {


    private ProprietarioRepository proprietarioRepository;


    @GetMapping
    public List<Proprietario> listar() {
        return proprietarioRepository.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long id) {
        return proprietarioRepository.findById(id)
                .map(proprietario -> ResponseEntity.ok(proprietario))
                .orElse(ResponseEntity.notFound().build());



    }


}
