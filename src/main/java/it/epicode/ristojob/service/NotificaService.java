package it.epicode.ristojob.service;

import it.epicode.ristojob.exception.NotFoundException;
import it.epicode.ristojob.model.*;
import it.epicode.ristojob.modelRequest.NotificaRequest;
import it.epicode.ristojob.repository.CandidaturaRepository;
import it.epicode.ristojob.repository.NotificaRepository;
import it.epicode.ristojob.repository.RecensioneRepository;
import it.epicode.ristojob.repository.UtenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificaService {
    @Autowired
    private NotificaRepository notificaRepository;
    @Autowired
    private CandidaturaRepository candidaturaRepository;
    @Autowired
    private RecensioneRepository recensioneRepository;
    @Autowired
    private UtenteRepository utenteRepository;

    public void inviaNotificaCandidatura(Candidatura candidatura){
        Notifica notifica = new Notifica();
        notifica.setAnnuncio(candidatura.getAnnuncio());
        notifica.setUtente(candidatura.getAnnuncio().getAzienda());
        notifica.setTesto("nuova candidatura per l'annuncio"+candidatura.getAnnuncio().getTitolo());
        notifica.getUtente().getNotifiche().add(notifica);
        notificaRepository.save(notifica);
    }
    public void inviaNotificaRecensione(Recensione recensione){
        Notifica notifica = new Notifica();
        notifica.setUtente(recensione.getUtenteDestinatario());
        notifica.setTesto("nuova recensione da parte di"+recensione.getUtenteRecensore().getNome());
        notifica.getUtente().getNotifiche().add(notifica);
        notificaRepository.save(notifica);

    }
    public Notifica idNotifica(int id) {
        return notificaRepository.findById(id).orElseThrow(()->new NotFoundException("notifica con id="+ id + " non trovato"));

    }
    public void deleteNotifia(int id)throws NotFoundException{
      Notifica notifica = idNotifica(id);

    }



    public List<Notifica> getAllNotifiche (int idUtente){
        Utente utente = utenteRepository.findById(idUtente).orElseThrow(()->new NotFoundException("utene con id="+ idUtente + " non trovato"));
        return notificaRepository.findByUtente(utente);

    }
}
