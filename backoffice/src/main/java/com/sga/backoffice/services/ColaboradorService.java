package com.sga.backoffice.services;

import com.sga.backoffice.controllers.request.ColaboradorRequest;
import com.sga.backoffice.controllers.response.ColaboradorResponse;
import com.sga.backoffice.entities.Colaborador;
import com.sga.backoffice.entities.Cracha;
import com.sga.backoffice.entities.PerfilAcesso;
import com.sga.backoffice.repositories.ColaboradorRepository;
import com.sga.backoffice.repositories.CrachaRepository;
import com.sga.backoffice.repositories.PerfilAcessoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {
    private final String CPF_ALREADY_REGISTERED = "Colaborador ja cadastrado.";
    private final String CRACHA_NOT_FOUND = "Cracha nao encontrado.";
    private final String PERFIL_NOT_FOUND = "Perfil de Acesso nao encontrado.";
    private final String ID_NOT_FOUND = "Colaborador nao encontrado";

    public List<ColaboradorResponse> getAll(ColaboradorRepository repository) {
        List<Colaborador> colaboradores = repository.findAll();

        List<ColaboradorResponse> responseList = new ArrayList<>();
        colaboradores.forEach(c -> responseList.add(adapter(c)));
        return responseList;
    }

    public ColaboradorResponse getById(ColaboradorRepository repository, Long id) {
        Optional<Colaborador> colaborador = repository.findById(id);

        if(colaborador.isPresent()){
           return adapter(colaborador.get());
        }

        return null;
    }

    public ColaboradorResponse getByCpf(ColaboradorRepository repository, String cpf) {
        Optional<Colaborador> colaborador = repository.findByCpf(cpf);

        if(colaborador.isPresent()){
            return adapter(colaborador.get());
        }

        return null;
    }

    private ColaboradorResponse adapter(Colaborador entity) {
        return new ColaboradorResponse(
                entity.getId(),
                entity.getNome(),
                entity.getCpf(),
                entity.getEmail(),
                entity.isAtivo(),
                entity.getCracha().getId(),
                entity.getPerfilAcesso().getId());
    }

    public List<String> create(ColaboradorRequest request,
                               ColaboradorRepository colaboradorRepository,
                               CrachaRepository crachaRepository,
                               PerfilAcessoRepository perfilRepository,
                               CrachaService crachaService) {

        Optional<Colaborador> colaborador = colaboradorRepository.findByCpf(request.getCpf());
        Optional<PerfilAcesso> perfilAcesso = perfilRepository.findById(request.getPerfilAcessoId());

        List<String> response = new ArrayList<>();

        if(colaborador.isPresent()){
            response.add(CPF_ALREADY_REGISTERED);
        }

        if(perfilAcesso.isEmpty()){
            response.add(PERFIL_NOT_FOUND);
        }

        if(!response.isEmpty()){
            return response;
        }

        Cracha cracha = crachaService.enable(request.getCrachaId(), crachaRepository);

        if(cracha == null){
            response.add(CRACHA_NOT_FOUND);
            return response;
        }

        colaboradorRepository.save(new Colaborador(request.getCpf(),
                request.getNome(),
                request.getEmail(),
                true,
                cracha,
                perfilAcesso.get()));

        return response;

    }

    public List<String> update(ColaboradorResponse request, ColaboradorRepository repository, CrachaRepository crachaRepository, PerfilAcessoRepository perfilRepository) {
        Optional<Colaborador> colaborador = repository.findById(request.getId());
        Optional<Cracha> cracha = crachaRepository.findById(request.getCrachaId());
        Optional<PerfilAcesso> perfilAcesso = perfilRepository.findById(request.getPerfilAcessoId());

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

        repository.save(new Colaborador(colaborador.get().getId(),
                                        request.getCpf(),
                                        request.getNome(),
                                        request.getEmail(),
                                        request.isAtivo(),
                                        cracha.get(),
                                        perfilAcesso.get()));
        return response;
    }
}
