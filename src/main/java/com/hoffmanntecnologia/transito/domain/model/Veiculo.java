package com.hoffmanntecnologia.transito.domain.model;

import com.hoffmanntecnologia.transito.domain.StatusVeiculo;
import com.hoffmanntecnologia.transito.domain.exception.NegocioException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity

public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    private Proprietario proprietario;

    private String marca;
    private String modelo;
    private String placa;

    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    private List<Autuacao> autuacoes = new ArrayList<>();

    public Autuacao adicionarAutuacao(Autuacao autuacao) {
        autuacao.setDataOcorrencia(OffsetDateTime.now());
        autuacao.setVeiculo(this);
        getAutuacoes().add(autuacao);
        return autuacao;
    }

    public void apreender() {

        if (estaAprendido()) {
            throw new NegocioException("Veiculo já se encontra apreendido");
        } else {
            setStatus(StatusVeiculo.APRRENDIDO);
            setDataApreensao(OffsetDateTime.now());
        }

    }

    @Transactional
    public void removerApreensao() {
        if (naoEstaApreendido()) {
            throw new NegocioException("Veiculo não esta apreendido");
        } else {
            setStatus(StatusVeiculo.REGULAR);
            setDataApreensao(null);
        }

    }


    public boolean estaAprendido() {
        return StatusVeiculo.APRRENDIDO.equals(getStatus());

    }

    public boolean naoEstaApreendido() {
        return StatusVeiculo.REGULAR.equals(getStatus());
    }

}
