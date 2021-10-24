package com.sga.backoffice.services;

import com.sga.backoffice.entities.Ambiente;
import com.sga.backoffice.repositories.AmbienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AmbienteService {
    @Autowired
    private AmbienteRepository repository;
    private final String AMBIENTE_ALREADY_REGISTERED = "Já existe um ambiente com o nome ";
    private final String ID_NOT_FOUND = "Colaborador nao encontrado";
    private final String RESPONSE_OK = "Operacao realizada com sucesso";

    public boolean create(Ambiente request) {
        Optional<Ambiente> ambiente = repository.findByNome(request.getNome());

        if(ambiente.isPresent()){
            return false;
        }

        repository.save(new Ambiente(request.getNome(), request.getDescricao(), request.getSentidoFluxo(),request.getNivelAcesso()));

        return true;
    }

    public boolean update(Ambiente request) {
        Optional<Ambiente> ambiente = repository.findById(request.getId());

        if(!ambiente.isPresent()){
            return false;
        }

        repository.save(new Ambiente(ambiente.get().getId(), request.getNome(), request.getDescricao(), request.getSentidoFluxo(),request.getNivelAcesso()));

        return true;
    }
}
