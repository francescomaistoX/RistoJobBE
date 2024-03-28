package it.epicode.ristojob.service;

import it.epicode.ristojob.exception.BadRequestException;
import it.epicode.ristojob.exception.NotFoundException;
import it.epicode.ristojob.model.*;
import it.epicode.ristojob.modelRequest.CandidaturaRequest;
import it.epicode.ristojob.modelResponse.AnnuncioDTO;
import it.epicode.ristojob.modelResponse.CandidaturaDTO;
import it.epicode.ristojob.repository.AnnuncioRepository;
import it.epicode.ristojob.repository.CandidaturaRepository;
import it.epicode.ristojob.repository.CollaboratoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidaturaService {
    @Autowired
    private CandidaturaRepository candidaturaRepository;
    @Autowired
    private AnnuncioService annuncioService;
    @Autowired
    private AnnuncioRepository annuncioRepository;
    @Autowired
    private NotificaService notificaService;
    @Autowired
    private CollaboratoreService collaboratoreService;
    @Autowired
    private CollaboratoreRepository collaboratoreRepository;
    public CandidaturaDTO saveCandidatura (CandidaturaDTO candidaturaDTO, int idAnnuncio,int idCandidato) throws BadRequestException {
        Candidatura candidatura = new Candidatura();
        Annuncio annuncio = annuncioService.idAnnuncio(idAnnuncio);
        Collaboratore collaboratore= collaboratoreService.idCollaboratore(idCandidato);
        candidatura.setAnnuncio(annuncio);
        candidatura.setCollaboratore(collaboratore);
        candidatura.setNomeCandidato(candidaturaDTO.getNomeCandidato());
        candidatura.setCognomeCandidato(candidaturaDTO.getCognomeCandidato());
        candidatura.setEmail(candidaturaDTO.getEmail());
        annuncio.getCandidature().add(candidatura);
        collaboratore.getCandidature().add(candidatura);
        annuncioRepository.save(annuncio);
        collaboratoreRepository.save(collaboratore);
        candidaturaRepository.save(candidatura);
        candidaturaDTO.setId(candidatura.getId());
        return candidaturaDTO;
    }
    public Candidatura idCandidatura (int id) {
        return candidaturaRepository.findById(id).orElseThrow(()->new NotFoundException("candidatura con id="+ id + " non trovato"));

    }


    public List<CandidaturaDTO> getAllCandidatureDto(int idAnnuncio) {
      Annuncio annuncio= annuncioService.idAnnuncio(idAnnuncio);
        List<Candidatura> candidature = candidaturaRepository.findByAnnuncio(annuncio);
        List<CandidaturaDTO> dtoCandidature = new ArrayList<>();

        for (Candidatura candidatura : candidature) {

        CandidaturaDTO dto = new CandidaturaDTO();

            dto.setId(candidatura.getId());
            dto.setNomeCandidato(candidatura.getNomeCandidato());
            dto.setCognomeCandidato(candidatura.getCognomeCandidato());
            dto.setEmail(candidatura.getEmail());
            dto.setIdAnnuncio(annuncio.getId());
            dto.setIdCollaboratore(candidatura.getCollaboratore().getId());


            dtoCandidature.add(dto);
        }
        return dtoCandidature;
    }

    public void deleteCandidatura(int id) {
        Candidatura candidatura = idCandidatura(id);
        candidaturaRepository.delete(candidatura);
    }

    public CandidaturaDTO converDTO (Candidatura candidatura) {
        CandidaturaDTO candidaturaDTO = new CandidaturaDTO();
        candidaturaDTO.setId(candidatura.getId());
        candidaturaDTO.setNomeCandidato(candidatura.getNomeCandidato());
        candidaturaDTO.setCognomeCandidato(candidatura.getCognomeCandidato());
        candidaturaDTO.setEmail(candidatura.getEmail());
        candidaturaDTO.setIdCollaboratore(candidatura.getCollaboratore().getId());

        return candidaturaDTO;
    }

    public CandidaturaDTO idCandidaturaDTO(int id) {
        Candidatura candidatura = candidaturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annuncio con id=" + id + " non trovato"));
        return converDTO(candidatura);
    }





}

