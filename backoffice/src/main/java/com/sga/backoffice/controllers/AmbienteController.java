package com.sga.backoffice.controllers;

import com.sga.backoffice.entities.Ambiente;
import com.sga.backoffice.repositories.AmbienteRepository;
import com.sga.backoffice.services.AmbienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ambiente")
public class AmbienteController {
    @Autowired
    private AmbienteRepository repository;
    @Autowired
    private AmbienteService service;

    @GetMapping()
    public ResponseEntity<List<Ambiente>> getAll(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ambiente> getById(@PathVariable Long id){
        Optional<Ambiente> ambiente = repository.findById(id);

        if(ambiente.isPresent()){
            return new ResponseEntity<Ambiente>(ambiente.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/nivel-acesso/{nivel}")
    public ResponseEntity<List<Ambiente>> getByNivelAcesso(@PathVariable int nivel){
        Optional<List<Ambiente>> ambiente = repository.findAllByNivelAcesso(nivel);

        if(ambiente.isPresent()){
            return new ResponseEntity<List<Ambiente>>(ambiente.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/novo")
    public ResponseEntity<String> newAmbiente(@RequestBody Ambiente ambiente){
        boolean sucess = service.create(ambiente);

        if(sucess){
            return new ResponseEntity<>("Ambiente cadastrado com sucesso", HttpStatus.OK);
        }

        return new ResponseEntity<>("Já existe um ambiente com o nome " + ambiente.getNome(), HttpStatus.CONFLICT);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Ambiente ambiente){
        boolean sucess = service.update(ambiente);

        if(sucess){
           return new ResponseEntity<>("Ambiente alterado com sucesso", HttpStatus.OK);
        }

        return new ResponseEntity<>("Ambiente nao encontrado. Confira o ID e tente novamente.", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Ambiente> ambiente = repository.findById(id);

        if(ambiente.isPresent()){
            repository.delete(ambiente.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
