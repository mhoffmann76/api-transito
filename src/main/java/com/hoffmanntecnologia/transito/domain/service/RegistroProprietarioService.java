package com.hoffmanntecnologia.transito.domain.service;

import com.hoffmanntecnologia.transito.domain.exception.NegocioException;
import com.hoffmanntecnologia.transito.domain.model.Proprietario;
import com.hoffmanntecnologia.transito.domain.repository.ProprietarioRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroProprietarioService {

    private final ProprietarioRepository proprietarioRepository;


    @Transactional
    public Proprietario salvar(@Valid Proprietario proprietario){
        boolean emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(p -> !p.equals(proprietario))
                .isPresent();

        if(emailEmUso){
            throw new NegocioException("Já existe um usuário cadastrado com esse e-mail");

        }

        return proprietarioRepository.save(proprietario);

    }
    @Transactional
    public void excluir(Long id){
        proprietarioRepository.deleteById(id);
    }


    public Proprietario buscar(Long id){
        return proprietarioRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Proprietario não encontrado"));


    }


}

