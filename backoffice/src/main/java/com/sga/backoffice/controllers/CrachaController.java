package com.sga.backoffice.controllers;

import com.sga.backoffice.entities.Cracha;
import com.sga.backoffice.repositories.CrachaRepository;
import com.sga.backoffice.services.CrachaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cracha")
public class CrachaController {
    @Autowired
    private CrachaRepository repository;
    @Autowired
    private CrachaService service;

    public CrachaController(CrachaRepository crachaRepository, CrachaService service) {
        this.repository = crachaRepository;
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Cracha>> getAll(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cracha> getById(@PathVariable Long id){
        Optional<Cracha> cracha = repository.findById(id);

        if(cracha.isPresent()){
            return new ResponseEntity<Cracha>(cracha.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/novo")
    public ResponseEntity<Cracha> newCracha(){
        Cracha response = service.create(repository);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/desativar/{id}")
    public ResponseEntity<Cracha> disable(@PathVariable Long id){
        Cracha response = service.disable(id, repository);

        if(!response.equals(null)){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Cracha> cracha = repository.findById(id);

        if(cracha.isPresent()){
            repository.delete(cracha.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
