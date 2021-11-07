package com.sga.backoffice.services;

import com.sga.backoffice.entities.Cracha;
import com.sga.backoffice.repositories.CrachaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrachaService {

    public Cracha create(CrachaRepository repository) {
        Cracha cracha = new Cracha(false);
        return repository.save(cracha);
    }

    public boolean disable(Long id, CrachaRepository repository) {
        Optional<Cracha> cracha = repository.findById(id);

        if(!cracha.isPresent()){
            return false;
        }

        cracha.get().setAtivo(false);
        repository.save(cracha.get());
        return true;
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
