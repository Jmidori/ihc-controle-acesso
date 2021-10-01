package com.sga.backoffice.repositories;

import com.sga.backoffice.entities.Ambiente;
import com.sga.backoffice.entities.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {
}
