package com.sga.backoffice.services;

import com.sga.backoffice.entities.Ambiente;
import com.sga.backoffice.entities.Colaborador;
import com.sga.backoffice.entities.Cracha;
import com.sga.backoffice.entities.PerfilAcesso;
import com.sga.backoffice.repositories.AmbienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AmbienteService {
    private final AmbienteRepository repository;
    private final String AMBIENTE_ALREADY_REGISTERED = "Já existe um ambiente com o nome ";
    private final String ID_NOT_FOUND = "Colaborador nao encontrado";

    public AmbienteService(AmbienteRepository repository) {
        this.repository = repository;
    }

    public String create(Ambiente request) {
        Optional<Ambiente> ambiente = repository.findByNome(request.getNome());

        if(ambiente.isPresent()){
            return AMBIENTE_ALREADY_REGISTERED.concat(request.getNome());
        }

        repository.save(new Ambiente(request.getNome(), request.getDescricao(), request.getNivelAcesso()));

        return null;
    }

    public String update(Ambiente request) {
        Optional<Ambiente> ambiente = repository.findById(request.getId());

        if(!ambiente.isPresent()){
            return ID_NOT_FOUND;
        }

        repository.save(new Ambiente(ambiente.get().getId(), request.getNome(), request.getDescricao(), request.getNivelAcesso()));

        return null;
    }
}
