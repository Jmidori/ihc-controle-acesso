package com.sga.backoffice.repositories;

import com.sga.backoffice.entities.GrupoAcesso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitanteRepository extends JpaRepository<GrupoAcesso, Long> {
}
