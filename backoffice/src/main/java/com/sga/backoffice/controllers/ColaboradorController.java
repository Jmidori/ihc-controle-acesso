package com.sga.backoffice.controllers;

import com.sga.backoffice.controllers.request.ColaboradorRequest;
import com.sga.backoffice.controllers.response.ColaboradorResponse;
import com.sga.backoffice.entities.Colaborador;
import com.sga.backoffice.repositories.ColaboradorRepository;
import com.sga.backoffice.repositories.CrachaRepository;
import com.sga.backoffice.repositories.PerfilAcessoRepository;
import com.sga.backoffice.services.ColaboradorService;
import com.sga.backoffice.services.CrachaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/colaborador")
public class ColaboradorController {
    @Autowired
    private ColaboradorRepository repository;
    @Autowired
    private CrachaRepository crachaRepository;
    @Autowired
    private PerfilAcessoRepository perfilAcessoRepository;
    @Autowired
    private ColaboradorService service;
    @Autowired
    private CrachaService crachaService;

    @GetMapping()
    public ResponseEntity<List<ColaboradorResponse>> getAll(){
        List<ColaboradorResponse> response = service.getAll(repository);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorResponse> getById(@PathVariable Long id){
        ColaboradorResponse response = service.getById(repository, id);

        if(response == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ColaboradorResponse> getByCpf(@PathVariable String cpf){
        ColaboradorResponse response = service.getByCpf(repository, cpf);
        Optional<Colaborador> colaborador = repository.findByCpf(cpf);

        if(response == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/novo")
    public ResponseEntity<List<String>> newColaborador(@RequestBody ColaboradorRequest request){
        List<String> response = service.create(request, repository, crachaRepository, perfilAcessoRepository, crachaService);

        if(response.size() == 0){
            response.add("Colaborador salvo com sucesso.");
            return new ResponseEntity(response, HttpStatus.CREATED);
        }

        return new ResponseEntity(response, HttpStatus.CONFLICT);
    }

    @PutMapping
    public ResponseEntity<List<String>> update(@RequestBody ColaboradorResponse colaborador){
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
