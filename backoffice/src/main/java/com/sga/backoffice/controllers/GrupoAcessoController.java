package com.sga.backoffice.controllers;

import com.sga.backoffice.controllers.request.Request;
import com.sga.backoffice.entities.Ambiente;
import com.sga.backoffice.entities.GrupoAcesso;
import com.sga.backoffice.entities.PerfilAcesso;
import com.sga.backoffice.repositories.AmbienteRepository;
import com.sga.backoffice.repositories.GrupoAcessoRepository;
import com.sga.backoffice.repositories.PerfilAcessoRepository;
import com.sga.backoffice.services.GrupoAcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grupo-acesso")
public class GrupoAcessoController {
    @Autowired
    private GrupoAcessoRepository repository;
    @Autowired
    private PerfilAcessoRepository perfilAcessoRepository;
    @Autowired
    private AmbienteRepository ambienteRepository;
    @Autowired
    private GrupoAcessoService service;

    @GetMapping()
    public ResponseEntity<List<GrupoAcesso>> getById(@RequestParam(required = false) Optional<String> perfilId,
                                               @RequestParam(required = false) Optional<String> ambienteId){
        List<GrupoAcesso> grupoAcessoList = new ArrayList<>();

        if(perfilId.isPresent()){

            if(ambienteId.isPresent()){
                Optional<GrupoAcesso> grupoAcesso = repository.findById(perfilId.get().concat(ambienteId.get()));

                if(grupoAcesso.isPresent()){
                    grupoAcessoList.add(grupoAcesso.get());
                }

            } else {

                grupoAcessoList = repository.findAllByPerfilAcesso(perfilId.get());
            }

        } else {

            grupoAcessoList = (ambienteId.isPresent() ?
                    repository.findAllByAmbiente(ambienteId.get()) :
                    repository.findAll());
        }

        if(grupoAcessoList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(grupoAcessoList, HttpStatus.OK);
    }

    @PostMapping("/novo")
    public ResponseEntity<String> create (@RequestBody Request request){

        Optional<PerfilAcesso> perfilAcesso = perfilAcessoRepository.findById(request.getPerfilAcessoId());
        if(!perfilAcesso.isPresent()){
            return new ResponseEntity<>("Perfil de acesso nao encontrado", HttpStatus.BAD_REQUEST);
        }

        Optional<Ambiente> ambiente = ambienteRepository.findById(request.getAmbienteId());
        if(!ambiente.isPresent()){
            return new ResponseEntity<>("Ambiente nao encontrado", HttpStatus.BAD_REQUEST);
        }

        boolean sucess = service.save(perfilAcesso.get(), ambiente.get());

        if(sucess){
            return new ResponseEntity<>("Ambiente adicionado com sucesso", HttpStatus.OK);
        }

        return new ResponseEntity<>("Este ambiente ja esta associado a um perfil de acesso", HttpStatus.CONFLICT);
    }

    @DeleteMapping()
    public ResponseEntity<?> delete(@RequestBody Request request){

        Optional<GrupoAcesso> grupoAcesso = repository.findById(String.valueOf(request.getPerfilAcessoId()).concat(String.valueOf(request.getAmbienteId())));

        if(!grupoAcesso.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        repository.delete(grupoAcesso.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
