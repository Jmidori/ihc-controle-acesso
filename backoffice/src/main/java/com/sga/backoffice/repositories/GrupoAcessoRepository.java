package com.sga.backoffice.repositories;

import com.sga.backoffice.entities.GrupoAcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GrupoAcessoRepository extends JpaRepository<GrupoAcesso, String> {
    @Query(value = "SELECT * FROM grupo_acesso WHERE pac_id = :perfilId", nativeQuery = true)
    List<GrupoAcesso> findAllByPerfilAcesso(@Param("perfilId") String perfilId);

    @Query(value = "SELECT * FROM grupo_acesso WHERE amb_id = :ambienteId", nativeQuery = true)
    List<GrupoAcesso> findAllByAmbiente(@Param("ambienteId") String ambienteId);

}
