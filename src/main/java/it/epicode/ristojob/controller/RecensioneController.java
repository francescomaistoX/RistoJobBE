package it.epicode.ristojob.controller;

import it.epicode.ristojob.exception.BadRequestException;
import it.epicode.ristojob.model.Annuncio;
import it.epicode.ristojob.model.Notifica;
import it.epicode.ristojob.model.Recensione;
import it.epicode.ristojob.modelRequest.AnnuncioRequest;
import it.epicode.ristojob.modelRequest.RecensioneRequest;
import it.epicode.ristojob.modelResponse.RecensioniDTO;
import it.epicode.ristojob.service.NotificaService;
import it.epicode.ristojob.service.RecensioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class RecensioneController {
    @Autowired
    private RecensioneService recensioneService;
    @GetMapping("/recensione")
    public List<RecensioniDTO> getAllForDestinatario(@RequestParam int idUtente){
        return recensioneService.getAllRecensioni(idUtente);

    }
    @PostMapping("/recensione")
    public RecensioniDTO saveRecensione(@RequestBody @Validated RecensioniDTO recensioniDTO, @RequestParam("idDestinatario") int idDestinatario, @RequestParam("idRecensore") int idRecensore , BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return  recensioneService.saveRecensione(recensioniDTO,idDestinatario,idRecensore);
    }
    @DeleteMapping("/recensione/{id}")
    public void deleteRecensione(int id){
        recensioneService.deleteRecensione(id);
    }

}
