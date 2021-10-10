package com.sga.backoffice.repositories;

import com.sga.backoffice.entities.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AcessoRepository extends JpaRepository<Acesso, Long> {

    List<Acesso> findByDataHoraBetween(Date initialDt, Date finalDt);

    List<Acesso> findByDataHoraBetweenAndAmbiente(Date initialDt, Date finalDt, Long ambiente);

    List<Acesso> findByDataHoraBetweenAndCracha(Date initialDt, Date finalDt, Long crachaId);

    List<Acesso> findByDataHoraBetweenAndCrachaAndAmbiente(Date initialDt, Date finalDt, Long crachaId, Long ambiente);
}
