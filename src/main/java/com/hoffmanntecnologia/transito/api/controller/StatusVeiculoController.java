package com.hoffmanntecnologia.transito.api.controller;

import com.hoffmanntecnologia.transito.domain.service.ApreensaoVeiculosService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/statusveiculo")

public class StatusVeiculoController {
    private final ApreensaoVeiculosService apreensaoVeiculosService;

    @PutMapping("/{veiculoId}/apreensao")
    public void apreender(@PathVariable Long veiculoId) {
        apreensaoVeiculosService.apreender(veiculoId);
    }

    @DeleteMapping("{veiculoId}/apreensao")

    public void removerApreensao(@PathVariable Long veiculoId) {
        apreensaoVeiculosService.removerApreensao(veiculoId);
    }
}
