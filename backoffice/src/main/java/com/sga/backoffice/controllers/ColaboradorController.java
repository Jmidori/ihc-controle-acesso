package com.sga.backoffice.controllers;

import com.sga.backoffice.controllers.request.ColaboradorRequest;
import com.sga.backoffice.entities.Colaborador;
import com.sga.backoffice.repositories.ColaboradorRepository;
import com.sga.backoffice.repositories.CrachaRepository;
import com.sga.backoffice.repositories.PerfilAcessoRepository;
import com.sga.backoffice.services.ColaboradorService;
import com.sga.backoffice.services.CrachaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/colaborador")
public class ColaboradorController {
    private final ColaboradorRepository repository;
    private final CrachaRepository crachaRepository;
    private final PerfilAcessoRepository perfilAcessoRepository;
    private final ColaboradorService service;
    private final CrachaService crachaService;

    public ColaboradorController(ColaboradorRepository repository, CrachaRepository crachaRepository, PerfilAcessoRepository perfilAcessoRepository, ColaboradorService service, CrachaService crachaService) {
        this.repository = repository;
        this.crachaRepository = crachaRepository;
        this.perfilAcessoRepository = perfilAcessoRepository;
        this.service = service;
        this.crachaService = crachaService;
    }

    @GetMapping()
    public ResponseEntity<List<Colaborador>> getAll(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> getById(@PathVariable Long id){
        Optional<Colaborador> colaborador = repository.findById(id);

        if(!colaborador.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Colaborador>(colaborador.get(), HttpStatus.OK);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Colaborador> getByCpf(@PathVariable String cpf){
        Optional<Colaborador> colaborador = repository.findByCpf(cpf);

        if(colaborador.isPresent()){
            return new ResponseEntity<Colaborador>(colaborador.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/novo")
    public ResponseEntity<List<String>> newColaborador(@RequestBody ColaboradorRequest request){
        List<String> response = service.create(request, repository, crachaRepository, perfilAcessoRepository, crachaService);

        if(response.isEmpty()){
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @PutMapping
    public ResponseEntity<List<String>> update(@RequestBody Colaborador colaborador){
        List<String> response = service.update(colaborador, repository, crachaRepository, perfilAcessoRepository);

        if(response.isEmpty()){
           return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Colaborador> colaborador = repository.findById(id);

        if(colaborador.isPresent()){
            repository.delete(colaborador.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
