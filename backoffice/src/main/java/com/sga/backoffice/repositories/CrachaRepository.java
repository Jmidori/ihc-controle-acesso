package com.sga.backoffice.repositories;

import com.sga.backoffice.entities.Acesso;
import com.sga.backoffice.entities.Cracha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrachaRepository extends JpaRepository<Cracha, Long> {
}
