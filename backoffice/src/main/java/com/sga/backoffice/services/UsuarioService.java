package com.sga.backoffice.services;

import com.sga.backoffice.controllers.request.UsuarioSignupRequest;
import com.sga.backoffice.entities.Usuario;
import com.sga.backoffice.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    public void signup(UsuarioSignupRequest request, UsuarioRepository repository) {
        Usuario usuario = new Usuario(
                request.isAdmin(),
                request.isRecepcao(),
                request.getPassword(),
                request.getColaborador());

        repository.save(usuario);
    }
}
