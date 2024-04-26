package com.hoffmanntecnologia.transito.domain.repository;

import com.hoffmanntecnologia.transito.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoReporitory extends JpaRepository<Veiculo, Long> {
    Optional<Veiculo> findByPlaca(String placa);
}
