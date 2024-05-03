package com.hoffmanntecnologia.transito.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProprietarioModel {
    private Long id;


    private String nome;

    private String email;


    private String telefone;
}
