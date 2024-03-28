package it.epicode.ristojob.controller;

import it.epicode.ristojob.exception.BadRequestException;
import it.epicode.ristojob.model.Candidatura;
import it.epicode.ristojob.model.Collaboratore;
import it.epicode.ristojob.modelRequest.CandidaturaRequest;
import it.epicode.ristojob.modelResponse.AnnuncioDTO;
import it.epicode.ristojob.modelResponse.CandidaturaDTO;
import it.epicode.ristojob.service.CandidaturaService;
import org.hibernate.validator.constraints.ru.INN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CandidaturaController {
    @Autowired
    private CandidaturaService candidaturaService;
    @GetMapping("/candidatura")
    public List<CandidaturaDTO> getAllForIdAnnuncio(@RequestParam int idAnnuncio){
        return candidaturaService.getAllCandidatureDto(idAnnuncio);

    }
    @GetMapping("/candidatura/Dto/{id}")
    public CandidaturaDTO annuncioDTO (@PathVariable int id){

        return candidaturaService.idCandidaturaDTO(id);
    }

    @PostMapping("/candidatura")
public CandidaturaDTO saveCandidatura (@RequestBody @Validated CandidaturaDTO candidaturaDTO, @RequestParam int idAnnuncio, @RequestParam int idCandidato , BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return  candidaturaService.saveCandidatura(candidaturaDTO,idAnnuncio,idCandidato);
    }
    @DeleteMapping("/candidatura/{id}")
    public void deleteannuncio(@PathVariable int id){
        candidaturaService.deleteCandidatura(id);
    }
}


