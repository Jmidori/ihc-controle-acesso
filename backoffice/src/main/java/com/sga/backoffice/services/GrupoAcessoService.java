package com.sga.backoffice.services;

import com.sga.backoffice.entities.Ambiente;
import com.sga.backoffice.entities.GrupoAcesso;
import com.sga.backoffice.entities.PerfilAcesso;
import com.sga.backoffice.repositories.GrupoAcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GrupoAcessoService {
    @Autowired
    private GrupoAcessoRepository repository;

    public boolean save(PerfilAcesso perfil, Ambiente ambiente){
        GrupoAcesso grupoAcesso = new GrupoAcesso(perfil, ambiente);

        if(repository.save(grupoAcesso).equals(null)){
           return false;
        }
        return true;
    }
}
