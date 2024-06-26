package com.hoffmanntecnologia.transito.domain.service;

import com.hoffmanntecnologia.transito.domain.StatusVeiculo;
import com.hoffmanntecnologia.transito.domain.exception.EntidadeNaoEcontradaException;
import com.hoffmanntecnologia.transito.domain.exception.NegocioException;
import com.hoffmanntecnologia.transito.domain.model.Proprietario;
import com.hoffmanntecnologia.transito.domain.model.Veiculo;
import com.hoffmanntecnologia.transito.domain.repository.ProprietarioRepository;
import com.hoffmanntecnologia.transito.domain.repository.VeiculoReporitory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {
    private final VeiculoReporitory veiculoReporitory;
    private final RegistroProprietarioService registroProprietarioService;


    public Veiculo buscar (Long veiculoId){
        return veiculoReporitory.findById(veiculoId)
                .orElseThrow(() -> new EntidadeNaoEcontradaException("Veiculo não encontrado"));
    }

    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo) {
        if (novoVeiculo.getId() != null) {
            throw new NegocioException("Veiculo a ser cadastrado não deve possuir um id");
        }
        boolean placaEmUso = veiculoReporitory.findByPlaca(novoVeiculo.getPlaca())
                .filter(veiculo -> !veiculo.equals(novoVeiculo))
                .isPresent();

        if (placaEmUso) {
            throw new NegocioException("Já existe um veiculo cadastrado com essa placa");
        }

        Proprietario proprietario = registroProprietarioService.buscar(novoVeiculo.getProprietario().getId());

        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(OffsetDateTime.now());


        return veiculoReporitory.save(novoVeiculo);

    }

}
