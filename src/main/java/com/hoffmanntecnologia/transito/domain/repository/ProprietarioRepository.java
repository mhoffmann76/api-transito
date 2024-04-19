package com.hoffmanntecnologia.transito.domain.repository;
import com.hoffmanntecnologia.transito.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

    List<Proprietario> findByNomeContaining(String nome);

}
