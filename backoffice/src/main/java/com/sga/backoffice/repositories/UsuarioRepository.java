package com.sga.backoffice.repositories;

import com.sga.backoffice.entities.Colaborador;
import com.sga.backoffice.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByColaborador(Long colaboradorId);

}
