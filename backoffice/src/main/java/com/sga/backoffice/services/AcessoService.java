package com.sga.backoffice.services;

import com.sga.backoffice.entities.Acesso;
import com.sga.backoffice.repositories.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AcessoService {
    @Autowired
    AcessoRepository repository;


    public List<Acesso> getAccessHistoryByRangeDate(Date initialDt, Date finalDt) {
        return repository.findByDataHoraBetween(initialDt, finalDt);
    }


    public List<Acesso> getAccessHistoryByRangeDateAndAmbiente(Date initialDt, Date finalDt, Long ambiente) {
        return repository.findByDataHoraBetweenAndAmbiente(initialDt, finalDt, ambiente);
    }


    public List<Acesso> getAccessHistoryByRangeDateAndCracha(Date initialDt, Date finalDt, Long crachaId) {
        return repository.findByDataHoraBetweenAndCracha(initialDt, finalDt, crachaId);
    }

    public List<Acesso> getAccessHistoryByRangeDateAndCrachaAndAmbiente(Date initialDt, Date finalDt, Long crachaId, Long ambiente) {
        return repository.findByDataHoraBetweenAndCrachaAndAmbiente(initialDt, finalDt, crachaId, ambiente);
    }
}
