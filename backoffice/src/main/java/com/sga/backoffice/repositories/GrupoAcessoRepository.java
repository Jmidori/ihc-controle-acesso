package com.sga.backoffice.repositories;

import com.sga.backoffice.entities.Colaborador;
import com.sga.backoffice.entities.GrupoAcesso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoAcessoRepository extends JpaRepository<GrupoAcesso, Long> {
}
