package com.sga.backoffice.controllers;

import com.sga.backoffice.entities.Acesso;
import com.sga.backoffice.entities.Ambiente;
import com.sga.backoffice.entities.Colaborador;
import com.sga.backoffice.repositories.AcessoRepository;
import com.sga.backoffice.repositories.AmbienteRepository;
import com.sga.backoffice.repositories.ColaboradorRepository;
import com.sga.backoffice.services.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/acesso")
public class AcessoController {

    @Autowired
    private AcessoRepository repository;

    @Autowired
    private ColaboradorRepository colaboradorRepo;

    @Autowired
    private AmbienteRepository ambienteRepo;
    @Autowired
    private AcessoService service;

    @GetMapping()
    public ResponseEntity<?> getColaboradorAccessHistory(@RequestParam(value = "dt-inicial") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date initialDt,
                                                   @RequestParam(value = "dt-final") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date finalDt,
                                                   @RequestParam(value = "colaborador", required = false) Optional<String> cpf,
                                                   @RequestParam(value = "ambiente-id", required = false) Optional<Long> ambienteId) throws ParseException {

        if(initialDt.after(finalDt)){
            return new ResponseEntity<>("Intervalo incorreto. Ajuste a data de inicio e fim da pesquisa.", HttpStatus.BAD_REQUEST);
        }

        List<Acesso> acessosHistory = new ArrayList<>();

        if(cpf.isPresent()){
            Optional<Colaborador> colaborador = colaboradorRepo.findByCpf(cpf.get());
            Long crachaId = null;

            if(colaborador.isPresent()){
               crachaId = colaborador.get().getCracha().getId();

            }else {

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if(ambienteId.isPresent()){
                Optional<Ambiente> ambiente = ambienteRepo.findById(ambienteId.get());

                if(!ambiente.isPresent()){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                acessosHistory = service.getAccessHistoryByRangeDateAndCrachaAndAmbiente(initialDt, finalDt, crachaId, ambienteId.get());

            } else {
                acessosHistory = service.getAccessHistoryByRangeDateAndCracha(initialDt, finalDt, crachaId);
            }

        } else {
            if (ambienteId.isPresent()) {
                Optional<Ambiente> ambiente = ambienteRepo.findById(ambienteId.get());

                if (!ambiente.isPresent()) {
                    return new ResponseEntity<>("O ambiente nao pode ser encontrado.", HttpStatus.BAD_REQUEST);
                }

                acessosHistory = service.getAccessHistoryByRangeDateAndAmbiente(initialDt, finalDt, ambienteId.get());
            }else {
                acessosHistory = service.getAccessHistoryByRangeDate(initialDt, finalDt);
            }
        }

        return new ResponseEntity<>(acessosHistory, HttpStatus.OK);
    }
}
