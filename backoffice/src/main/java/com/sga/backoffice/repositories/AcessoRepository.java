package com.sga.backoffice.repositories;

import com.sga.backoffice.entities.Acesso;
import com.sga.backoffice.entities.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcessoRepository extends JpaRepository<Acesso, Long> {
}
