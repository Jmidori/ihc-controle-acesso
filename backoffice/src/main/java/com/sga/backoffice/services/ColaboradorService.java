package com.sga.backoffice.services;

import com.sga.backoffice.controllers.request.ColaboradorRequest;
import com.sga.backoffice.controllers.response.ColaboradorResponse;
import com.sga.backoffice.entities.Colaborador;
import com.sga.backoffice.entities.Cracha;
import com.sga.backoffice.entities.PerfilAcesso;
import com.sga.backoffice.repositories.ColaboradorRepository;
import com.sga.backoffice.repositories.CrachaRepository;
import com.sga.backoffice.repositories.PerfilAcessoRepository;
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
        return adapter(colaboradores);
    }

    private List<ColaboradorResponse> adapter(List<Colaborador> colaboradores) {
        List<ColaboradorResponse> responseList = new ArrayList<>();
        colaboradores.forEach(c -> responseList.add(
                new ColaboradorResponse(c.getCpf(),
                        c.getNome(),
                        c.getEmail(),
                        c.getCracha().getId(),
                        c.getPerfilAcesso().getId())));
        return responseList;
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

        if(response.size() > 0 || response.isEmpty()){
            return response;
        }

        Cracha cracha = crachaService.enable(request.getCrachaId(), crachaRepository);

        if(cracha.equals(null)){
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
