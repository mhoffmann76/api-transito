package com.hoffmanntecnologia.transito.api.assembler;

import com.hoffmanntecnologia.transito.api.model.VeiculoModel;
import com.hoffmanntecnologia.transito.api.model.input.VeiculoInput;
import com.hoffmanntecnologia.transito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class VeiculoAssembler {
    private final ModelMapper modelMapper;

    public Veiculo toEntity(VeiculoInput veiculoInput){
        return modelMapper.map(veiculoInput, Veiculo.class);
    }

    public VeiculoModel toModel(Veiculo veiculo){
        return modelMapper.map(veiculo, VeiculoModel.class);
    }

    public List<VeiculoModel> toCollectionModel(List<Veiculo> veiculos){
        return veiculos.stream()
                .map(this::toModel)
                .toList();

    }


}
