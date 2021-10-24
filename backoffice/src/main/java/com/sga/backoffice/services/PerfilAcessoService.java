package com.sga.backoffice.services;

import com.sga.backoffice.controllers.request.PerfilAcessoRequest;
import com.sga.backoffice.entities.PerfilAcesso;
import com.sga.backoffice.repositories.PerfilAcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerfilAcessoService {
    @Autowired
    private PerfilAcessoRepository repository;

    public boolean create(PerfilAcessoRequest request) {
        Optional<PerfilAcesso> perfilAcesso = repository.findByNome(request.getNome());

        if(perfilAcesso.isPresent()){
            return false;
        }

        repository.save(new PerfilAcesso(request.getNome(), request.getDescricao()));

        return true;
    }

    public boolean update(PerfilAcesso request) {
        Optional<PerfilAcesso> perfilAcesso = repository.findById(request.getId());

        if(!perfilAcesso.isPresent()){
            return false;
        }

        repository.save(new PerfilAcesso(request.getNome(), request.getDescricao()));

        return true;
    }
}
