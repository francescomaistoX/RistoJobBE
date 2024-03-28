package it.epicode.ristojob.controller;

import it.epicode.ristojob.exception.BadRequestException;
import it.epicode.ristojob.model.Annuncio;
import it.epicode.ristojob.modelRequest.AnnuncioRequest;
import it.epicode.ristojob.modelResponse.AnnuncioDTO;
import it.epicode.ristojob.service.AnnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnnuncioController {
    @Autowired
    private AnnuncioService annuncioService;

    @PostMapping("/annuncio")
    public AnnuncioDTO saveAnnuncio(@RequestBody @Validated AnnuncioDTO annuncioDTO, @RequestParam("idAzienda") int idAzienda, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        annuncioService.saveAnnuncio(annuncioDTO,idAzienda);
        return annuncioDTO;
    }
    @GetMapping("/annuncio/{id}")
    public Annuncio annuncio (@PathVariable int id){

        return annuncioService.idAnnuncio(id);
    }
    @GetMapping("/annuncio/Dto/{id}")
    public AnnuncioDTO annuncioDTO (@PathVariable int id){

        return annuncioService.idAnnuncioDto(id);
    }
    @PutMapping("/annuncio/{id}/upload")
    public AnnuncioDTO updateAnnuncio(@PathVariable int id, @RequestBody @Validated AnnuncioDTO annuncioDTO , @RequestParam("idAzienda") int idAzienda,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return annuncioService.upLoadAnnuncio(annuncioDTO,id,idAzienda) ;
    }
    @GetMapping("/annuncio/azienda")
    public List<AnnuncioDTO> getAllForIdAzienda(@RequestParam int idAzienda){
        return annuncioService.getAllAnnunci(idAzienda);

    }
    @GetMapping("/annuncio")
    public List<AnnuncioDTO> getAllAnnunci (){
     return annuncioService.getAllAnnunci();
    }


    @DeleteMapping("/annuncio/{id}")
    public void deleteannuncio(@PathVariable int id){
        annuncioService.deleteAnnuncio(id);
    }
}



