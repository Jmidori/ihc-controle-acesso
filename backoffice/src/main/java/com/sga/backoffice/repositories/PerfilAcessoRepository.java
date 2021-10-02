package com.sga.backoffice.repositories;

import com.sga.backoffice.entities.GrupoAcesso;
import com.sga.backoffice.entities.PerfilAcesso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilAcessoRepository extends JpaRepository<PerfilAcesso, Long> {
}
