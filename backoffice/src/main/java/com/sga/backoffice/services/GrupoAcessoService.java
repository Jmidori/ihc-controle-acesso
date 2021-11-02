package com.sga.backoffice.services;

import com.sga.backoffice.entities.Ambiente;
import com.sga.backoffice.entities.GrupoAcesso;
import com.sga.backoffice.entities.GrupoAcessoPK;
import com.sga.backoffice.entities.PerfilAcesso;
import com.sga.backoffice.repositories.GrupoAcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Service
public class GrupoAcessoService {
    @Autowired
    private GrupoAcessoRepository repository;

    @Autowired
    DataSource dataSource;

//    public boolean create(PerfilAcesso perfilAcesso, Ambiente ambiente) {
//
//        GrupoAcessoPK id = new GrupoAcessoPK(perfilAcesso.getId(), ambiente.getId());
//        Optional<GrupoAcesso> grupoAcesso = repository.findById(id);
//
//        if(grupoAcesso.isPresent()){
//            return false;
//        }
//
//        repository.save(new GrupoAcesso(perfilAcesso.getId(), ambiente.getId()));
//
//        return true;
//    }

    public Iterable<GrupoAcesso> getAll(){
        return repository.findAll();
    }

    public List<GrupoAcesso> getAllByAmbiente(Long ambienteId){
        return repository.findAllByAmbiente(String.valueOf(ambienteId));
    }

    public List<GrupoAcesso> getAllByPerfil(Long perfilId){
        return repository.findAllByPerfilAcesso(String.valueOf(perfilId));
    }

    public boolean save(PerfilAcesso perfil, Ambiente ambiente){
        GrupoAcesso grupoAcesso = new GrupoAcesso(perfil, ambiente);

        if(repository.save(grupoAcesso).equals(null)){
           return false;
        }
        return true;
    }
}
