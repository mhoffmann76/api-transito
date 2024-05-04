package com.hoffmanntecnologia.transito.api.controller;

import com.hoffmanntecnologia.transito.domain.service.ApreensaoVeiculosService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/statusveiculo")

public class StatusVeiculoController {
    private final ApreensaoVeiculosService apreensaoVeiculosService;

    @PutMapping("/{veiculoId}/apreender")
    public void apreender(@PathVariable Long veiculoId) {
        apreensaoVeiculosService.apreender(veiculoId);
    }

    @DeleteMapping("{veiculoId}/liberar")

    public void removerApreensao(@PathVariable Long veiculoId) {
        apreensaoVeiculosService.removerApreensao(veiculoId);
    }
}
