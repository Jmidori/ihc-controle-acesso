package com.sga.backoffice.services;

import com.sga.backoffice.entities.Cracha;
import com.sga.backoffice.repositories.CrachaRepository;

import java.util.Optional;

public class CrachaService {

    public Cracha create(CrachaRepository repository) {
        Cracha cracha = new Cracha(false);
        return repository.save(cracha);
    }

    public Cracha disable(Long id, CrachaRepository repository) {
        Optional<Cracha> cracha = repository.findById(id);

        if(!cracha.isPresent()){
            return null;
        }

        cracha.get().setAtivo(false);
        return repository.save(cracha.get());
    }

    public Cracha enable(Long id, CrachaRepository repository) {
        Optional<Cracha> cracha = repository.findById(id);

        if(!cracha.isPresent()){
            return null;
        }
        cracha.get().setAtivo(true);
        return repository.save(cracha.get());
    }
}
