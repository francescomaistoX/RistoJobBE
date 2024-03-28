package it.epicode.ristojob.service;

import it.epicode.ristojob.exception.BadRequestException;
import it.epicode.ristojob.exception.NotFoundException;
import it.epicode.ristojob.model.*;
import it.epicode.ristojob.modelRequest.RecensioneRequest;
import it.epicode.ristojob.modelResponse.AnnuncioDTO;
import it.epicode.ristojob.modelResponse.CandidaturaDTO;
import it.epicode.ristojob.modelResponse.RecensioniDTO;
import it.epicode.ristojob.repository.RecensioneRepository;
import it.epicode.ristojob.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecensioneService {
    @Autowired
    private RecensioneRepository recensioneRepository;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private CollaboratoreService collaboratoreService;
    @Autowired
    private NotificaService notificaService;
    public RecensioniDTO saveRecensione (RecensioniDTO recensioniDTO, int idDestinatario, int idRecensore){
      Recensione recensione= new Recensione();
      Utente destinatario = utenteRepository.findById(idDestinatario).orElseThrow(()->new NotFoundException("Autore con id="+ idRecensore + " non trovato"));
      Utente recensore = utenteRepository.findById(idRecensore).orElseThrow(()->new NotFoundException("Autore con id="+ idRecensore + " non trovato"));
      recensione.setData(LocalDate.now());
      recensione.setUtenteRecensore(recensore);
      recensione.setUtenteDestinatario(destinatario);
      recensione.setTesto(recensioniDTO.getTesto());
      destinatario.getRecensioniRicevute().add(recensione);
      recensore.getRecensioniLasciate().add(recensione);
      utenteRepository.save(destinatario);
      utenteRepository.save(recensore);
       recensioneRepository.save(recensione);
      recensioniDTO.setId(recensione.getId());
        return recensioniDTO;
    }

    public List<RecensioniDTO> getAllRecensioni (int idDestinatario){
        Utente utente = utenteRepository.findById(idDestinatario).orElseThrow(()->new NotFoundException("Autore con id="+ idDestinatario+ " non trovato"));
        List<Recensione> recensioni = recensioneRepository.findByUtenteDestinatario(utente);
        List<RecensioniDTO> dtorecensioni = new ArrayList<>();
        for (Recensione recensione :recensioni) {
           RecensioniDTO dto = new RecensioniDTO();
            if (recensione.getUtenteRecensore()!=null){
                dto.setUtenteRecensore(recensione.getUtenteRecensore().getNome());
            }
            dto.setData(recensione.getData());
            dto.setTesto(recensione.getTesto());
            dtorecensioni.add(dto);
        }
         return dtorecensioni;
    }
    public Recensione idRecenciose (int id){
        return recensioneRepository.findById(id).orElseThrow(()->new NotFoundException("Recensione con id="+ id + " non trovato"));
    }
    public void deleteRecensione (int id){
        Recensione recensione = idRecenciose(id);
        recensioneRepository.delete(recensione);
    }
    public RecensioniDTO convertToDto(Recensione recensione){
        RecensioniDTO recensioneDTO= new RecensioniDTO();
        recensioneDTO.setId(recensione.getId());
        recensioneDTO.setData(LocalDate.now());
        recensioneDTO.setTesto(recensione.getTesto());
        if (recensione.getUtenteRecensore()!=null){
        recensioneDTO.setUtenteRecensore(recensione.getUtenteRecensore().getNome());
        }
        return recensioneDTO;
    }
    public RecensioniDTO idRecensioneDTO(int id) {
        Recensione recensione = recensioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annuncio con id=" + id + " non trovato"));
        return convertToDto(recensione);
    }



}
