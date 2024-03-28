package it.epicode.ristojob.service;

import it.epicode.ristojob.exception.BadRequestException;
import it.epicode.ristojob.exception.NotFoundException;
import it.epicode.ristojob.model.Annuncio;
import it.epicode.ristojob.model.Azienda;
import it.epicode.ristojob.model.Collaboratore;
import it.epicode.ristojob.modelRequest.AziendaRequest;
import it.epicode.ristojob.modelRequest.CollaboratoreRequest;
import it.epicode.ristojob.modelResponse.UtenteBaseDTO;
import it.epicode.ristojob.repository.AziendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AziendaService {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private AziendaRepository aziendaRepository;
    public Azienda saveAzienda(AziendaRequest aziendaRequest)throws BadRequestException {
       Azienda azienda = new Azienda();
       azienda.setNome(aziendaRequest.getNome());
       azienda.setEmail(aziendaRequest.getEmail());
       azienda.setPassword(encoder.encode(aziendaRequest.getPassword()));
       azienda.setComune(aziendaRequest.getComune());
       azienda.setRegione(aziendaRequest.getRegione());
       return aziendaRepository.save(azienda);}
    public Azienda idAzienda(int id){
        return aziendaRepository.findById(id).orElseThrow(()->new NotFoundException("Autore con id="+ id + " non trovato"));
    }
    public Azienda getUtenteByEmail(String email){
        return aziendaRepository.findByEmail(email).orElseThrow(()->new NotFoundException("Username non trovato"));
    }
    public UtenteBaseDTO upLoadAzienda(int id, UtenteBaseDTO aziendaDto)throws NotFoundException{
        Azienda azienda= idAzienda(id);
        azienda.setNome(aziendaDto.getNome());
        azienda.setEmail(aziendaDto.getEmail());
       azienda.setPassword(encoder.encode(aziendaDto.getPassword()));
        azienda.setComune(aziendaDto.getComune());
        azienda.setRegione(aziendaDto.getRegione());
        aziendaRepository.save(azienda);
        aziendaDto.setId(azienda.getId());
        return aziendaDto;

    }

    public void deleteAzienda(int id) throws NotFoundException{
       Azienda azienda= idAzienda(id);
        aziendaRepository.delete(azienda);
    }

    public UtenteBaseDTO convertToDTO(Azienda azienda) {
        UtenteBaseDTO aziendaDTO = new UtenteBaseDTO();
        aziendaDTO.setId(azienda.getId());
        aziendaDTO.setNome(azienda.getNome());
       aziendaDTO.setEmail(azienda.getEmail());
        aziendaDTO.setComune(azienda.getComune());
        aziendaDTO.setRegione(azienda.getRegione());


        // Altri campi da convertire

        return aziendaDTO;
    }

    public UtenteBaseDTO idAziendaDTO(int id) {
        Azienda azienda = aziendaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annuncio con id=" + id + " non trovato"));
        return convertToDTO(azienda);
    }

}

