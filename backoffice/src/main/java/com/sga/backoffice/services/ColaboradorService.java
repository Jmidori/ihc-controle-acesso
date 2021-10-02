package com.sga.backoffice.services;

import com.sga.backoffice.controllers.request.ColaboradorRequest;
import com.sga.backoffice.entities.Colaborador;
import com.sga.backoffice.entities.Cracha;
import com.sga.backoffice.entities.PerfilAcesso;
import com.sga.backoffice.repositories.ColaboradorRepository;
import com.sga.backoffice.repositories.CrachaRepository;
import com.sga.backoffice.repositories.PerfilAcessoRepository;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ColaboradorService {
    private final String CPF_ALREADY_REGISTERED = "Colaborador ja cadastrado.";
    private final String CRACHA_NOT_FOUND = "Cracha nao encontrado.";
    private final String PERFIL_NOT_FOUND = "Perfil de Acesso nao encontrado.";
    private final String ID_NOT_FOUND = "Colaborador nao encontrado";

    public List<String> createColaborador(ColaboradorRequest request,
                                          ColaboradorRepository colaboradorRepository,
                                          CrachaRepository crachaRepository,
                                          PerfilAcessoRepository perfilRepository) {

        Optional<Colaborador> colaborador = colaboradorRepository.findByCpf(request.getCpf());
        Optional<Cracha> cracha = crachaRepository.findById(request.getCrachaId());
        Optional<PerfilAcesso> perfilAcesso = perfilRepository.findById(request.getPerfilAcessoId());

        List<String> response = new ArrayList<>();

        if(colaborador.isPresent()){
            response.add(CPF_ALREADY_REGISTERED);
        }

        if(cracha.isEmpty()){
            response.add(CRACHA_NOT_FOUND);
        }

        if(perfilAcesso.isEmpty()){
            response.add(PERFIL_NOT_FOUND);
        }

        if(!response.isEmpty()){
            return response;
        }

        Cracha crachaAtivo = activeCracha(cracha.get(), crachaRepository);

        colaboradorRepository.save(new Colaborador(request.getCpf(),
                request.getNome(),
                request.getEmail(),
                crachaAtivo,
                perfilAcesso.get()));

        return response;

    }

    public Cracha activeCracha(Cracha cracha, CrachaRepository repository) {
        cracha.setAtivo(true);
        return repository.save(cracha);
    }

    public List<String> update(Colaborador request, ColaboradorRepository repository, CrachaRepository crachaRepository, PerfilAcessoRepository perfilRepository) {
        Optional<Colaborador> colaborador = repository.findById(request.getId());
        Optional<Cracha> cracha = crachaRepository.findById(request.getCracha().getId());
        Optional<PerfilAcesso> perfilAcesso = perfilRepository.findById(request.getPerfilAcesso().getId());

        List<String> response = new ArrayList<>();

        if(colaborador.isEmpty()){
            response.add(ID_NOT_FOUND);
        }

        if(cracha.isEmpty()){
            response.add(CRACHA_NOT_FOUND);
        }

        if(perfilAcesso.isEmpty()){
            response.add(PERFIL_NOT_FOUND);
        }

        if(!response.isEmpty()){
            return response;
        }

        colaborador.get().setCpf(request.getCpf());
        colaborador.get().setNome(request.getNome());
        colaborador.get().setEmail(request.getEmail());
        colaborador.get().setCracha(cracha.get());
        colaborador.get().setPerfilAcesso(perfilAcesso.get());

        repository.save(colaborador.get());
        return response;
    }

}
