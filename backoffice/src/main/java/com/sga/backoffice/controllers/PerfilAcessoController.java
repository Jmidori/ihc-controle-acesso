package com.sga.backoffice.controllers;

import com.sga.backoffice.controllers.request.PerfilAcessoRequest;
import com.sga.backoffice.entities.PerfilAcesso;
import com.sga.backoffice.repositories.PerfilAcessoRepository;
import com.sga.backoffice.services.PerfilAcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/perfil-acesso")
public class PerfilAcessoController {
    @Autowired
    private PerfilAcessoRepository repository;
    @Autowired
    private PerfilAcessoService service;


    @GetMapping()
    public ResponseEntity<List<PerfilAcesso>> getAll(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilAcesso> getById(@PathVariable Long id){
        Optional<PerfilAcesso> perfilAcesso = repository.findById(id);

        if(perfilAcesso.isPresent()){
            return new ResponseEntity<PerfilAcesso>(perfilAcesso.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/novo")
    public ResponseEntity<String> create(@RequestBody PerfilAcessoRequest perfilAcesso){
        boolean sucess = service.create(perfilAcesso);

        if(sucess){
            return new ResponseEntity<>("Perfil de acesso cadastrado com sucesso", HttpStatus.OK);
        }

        return new ResponseEntity<>("Já existe um perfil de acesso com o nome " + perfilAcesso.getNome(), HttpStatus.CONFLICT);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody PerfilAcesso perfilAcesso){
        boolean sucess = service.update(perfilAcesso);

        if(sucess){
           return new ResponseEntity<>("Perfil de acesso alterado com sucesso", HttpStatus.OK);
        }

        return new ResponseEntity<>("Perfil de acesso nao encontrado. Confira o ID e tente novamente.", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<PerfilAcesso> perfilAcesso = repository.findById(id);

        if(perfilAcesso.isPresent()){
            repository.delete(perfilAcesso.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
