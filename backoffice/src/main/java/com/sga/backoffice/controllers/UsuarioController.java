package com.sga.backoffice.controllers;

import com.sga.backoffice.controllers.request.UsuarioSignupRequest;
import com.sga.backoffice.entities.Colaborador;
import com.sga.backoffice.entities.Usuario;
import com.sga.backoffice.repositories.ColaboradorRepository;
import com.sga.backoffice.repositories.UsuarioRepository;
import com.sga.backoffice.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private ColaboradorRepository colaboradorRepository;
    @Autowired
    private UsuarioService service;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UsuarioSignupRequest request){
        Optional<Usuario> usuario = repository.findByColaborador(request.getColaborador().getId());
        if(usuario.isPresent()){
            return new ResponseEntity<>("Usuario já possui cadastro",HttpStatus.BAD_REQUEST);
        }
        service.signup(request, repository);
        return new ResponseEntity<>("Cadastro realizado com scesso", HttpStatus.CREATED);
    }

//    @GetMapping("/login")
//    @PutMapping("/esqueci-senha")
//    @DeleteMapping()
}
