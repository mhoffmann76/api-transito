package com.hoffmanntecnologia.transito.api.controller;

import com.hoffmanntecnologia.transito.api.assembler.ProprietarioAssembler;
import com.hoffmanntecnologia.transito.api.model.ProprietarioModel;
import com.hoffmanntecnologia.transito.api.model.VeiculoModel;
import com.hoffmanntecnologia.transito.api.model.input.ProprietarioInput;
import com.hoffmanntecnologia.transito.api.model.input.VeiculoInput;
import com.hoffmanntecnologia.transito.domain.exception.NegocioException;
import com.hoffmanntecnologia.transito.domain.model.Proprietario;
import com.hoffmanntecnologia.transito.domain.model.Veiculo;
import com.hoffmanntecnologia.transito.domain.repository.ProprietarioRepository;
import com.hoffmanntecnologia.transito.domain.service.RegistroProprietarioService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
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

    private RegistroProprietarioService registroProprietarioService;
    private ProprietarioRepository proprietarioRepository;
    private ProprietarioAssembler proprietarioAssembler;







    @GetMapping
    public List<ProprietarioModel> listar() {
        return proprietarioAssembler.toCollectionModel(proprietarioRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProprietarioModel> buscar(@PathVariable Long id) {
        return proprietarioRepository.findById(id)
                .map(proprietarioAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());


    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProprietarioModel cadastrar(@Valid @RequestBody ProprietarioInput proprietarioInput){
        Proprietario novoProprietario = proprietarioAssembler.toEntity(proprietarioInput);
        Proprietario proprietarioCadastrado = registroProprietarioService.salvar(novoProprietario);
        return proprietarioAssembler.toModel(proprietarioCadastrado);

    }



    @PutMapping("/{id}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long id,
                                                  @Valid @RequestBody Proprietario proprietario) {
        if (!proprietarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        proprietario.setId(id);
        Proprietario proprietarioAtualizado = registroProprietarioService.salvar(proprietario);

        return ResponseEntity.ok(proprietarioAtualizado);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!proprietarioRepository.existsById(id)) {

            return ResponseEntity.notFound().build();


        }
        registroProprietarioService.excluir(id);
        return ResponseEntity.noContent().build();


    }



}
