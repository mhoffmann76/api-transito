package com.hoffmanntecnologia.transito.api.controller;

import com.hoffmanntecnologia.transito.domain.model.Proprietario;
import com.hoffmanntecnologia.transito.domain.repository.ProprietarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario Adicionar(@RequestBody Proprietario proprietario) {
        return proprietarioRepository.save(proprietario);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long id,
                                                  @RequestBody Proprietario proprietario) {
        if (!proprietarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        proprietario.setId(id);
        Proprietario proprietarioAtualizado = proprietarioRepository.save(proprietario);

        return ResponseEntity.ok(proprietarioAtualizado);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!proprietarioRepository.existsById(id)) {

            return ResponseEntity.notFound().build();


        }
        proprietarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();


    }


}
