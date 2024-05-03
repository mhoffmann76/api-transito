package com.hoffmanntecnologia.transito.domain.service;

import com.hoffmanntecnologia.transito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ApreensaoVeiculosService {
    private final RegistroVeiculoService registroVeiculoService;

    @Transactional
    public void apreender(Long veiculoId) {
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
        veiculo.apreender();

    }

    @Transactional
    public void removerApreensao(Long veiculoID){
        Veiculo veiculo = registroVeiculoService.buscar(veiculoID);
        veiculo.removerApreensao();

    }

}
