package com.sga.backoffice.repositories;

import com.sga.backoffice.entities.Ambiente;
import com.sga.backoffice.entities.GrupoAcesso;
import com.sga.backoffice.entities.PerfilAcesso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilAcessoRepository extends JpaRepository<PerfilAcesso, Long> {
    Optional<PerfilAcesso> findByNome(String nome);
}
