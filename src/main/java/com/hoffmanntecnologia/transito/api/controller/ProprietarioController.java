package com.hoffmanntecnologia.transito.api.controller;

import com.hoffmanntecnologia.transito.domain.model.Proprietario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProprietarioController {

    @GetMapping("/proprietarios")
    public List<Proprietario> listar() {
        var proprietario1 = new Proprietario();
        proprietario1.setId(1L);
        proprietario1.setNome("João");
        proprietario1.setEmail("joao@ig.com.br");
        proprietario1.setTelefone("2799909-0909");

        var proprietario2 = new Proprietario();
        proprietario2.setId(2L);
        proprietario2.setNome("Maria");
        proprietario2.setEmail("Maria@ig.com.br");
        proprietario2.setTelefone("2799919-0609");

        return Arrays.asList(proprietario1, proprietario2);


    }


}
