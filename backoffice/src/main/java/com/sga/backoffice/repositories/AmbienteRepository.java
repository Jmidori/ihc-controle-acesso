package com.sga.backoffice.repositories;

import com.sga.backoffice.entities.Ambiente;
import com.sga.backoffice.entities.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {
    Optional<List<Ambiente>> findAllByNivelAcesso(int nivel);

    Optional<Ambiente> findByNome(String nome);
}
