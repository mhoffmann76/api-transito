package com.hoffmanntecnologia.transito.domain.service;

import com.hoffmanntecnologia.transito.domain.model.Autuacao;
import com.hoffmanntecnologia.transito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service

public class RegistroAutuacaoService {
    private RegistroVeiculoService registroVeiculoService;

    @Transactional
    public Autuacao registrar(long veiculoId, Autuacao novaAutuacao){
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
        return  veiculo.adicionarAutuacao(novaAutuacao);

    }
}
