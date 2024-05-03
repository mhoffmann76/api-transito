package com.hoffmanntecnologia.transito.api.assembler;

import com.hoffmanntecnologia.transito.api.model.ProprietarioModel;
import com.hoffmanntecnologia.transito.api.model.input.ProprietarioInput;
import com.hoffmanntecnologia.transito.domain.model.Proprietario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ProprietarioAssembler {
    private final ModelMapper modelMapper;

    public Proprietario toEntity(ProprietarioInput proprietarioInput){
        return this.modelMapper.map(proprietarioInput, Proprietario.class);


    }

    public ProprietarioModel toModel(Proprietario proprietario){
        return modelMapper.map(proprietario, ProprietarioModel.class);
    }

    public List<ProprietarioModel> toCollectionModel(List<Proprietario> proprietarios){
        return proprietarios.stream()
                .map(this::toModel)
                .toList();

    }
}

