package it.epicode.ristojob.service;

import it.epicode.ristojob.exception.BadRequestException;
import it.epicode.ristojob.exception.NotFoundException;
import it.epicode.ristojob.model.Annuncio;
import it.epicode.ristojob.model.Azienda;
import it.epicode.ristojob.model.Collaboratore;
import it.epicode.ristojob.modelRequest.CollaboratoreRequest;
import it.epicode.ristojob.modelResponse.AnnuncioDTO;
import it.epicode.ristojob.modelResponse.UtenteBaseDTO;
import it.epicode.ristojob.repository.CollaboratoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CollaboratoreService {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private CollaboratoreRepository collaboratoreRepository;
    public Collaboratore saveCollaboratore(CollaboratoreRequest collaboratoreRequest)throws BadRequestException {
        Collaboratore collaboratore = new Collaboratore();
        collaboratore.setNome(collaboratoreRequest.getNome());
        collaboratore.setCognome(collaboratoreRequest.getCognome());
        collaboratore.setEmail(collaboratoreRequest.getEmail());
        collaboratore.setPassword(encoder.encode(collaboratoreRequest.getPassword()));
        collaboratore.setComune(collaboratoreRequest.getComune());
        collaboratore.setRegione(collaboratoreRequest.getRegione());


        return collaboratoreRepository.save(collaboratore);

    }
    public Collaboratore idCollaboratore(int id){
        return collaboratoreRepository.findById(id).orElseThrow(()->new NotFoundException("Autore con id="+ id + " non trovato"));
    }
    public Collaboratore getUtenteByEmail(String email){
        return collaboratoreRepository.findByEmail(email).orElseThrow(()->new NotFoundException("Username non trovato"));
    }
    public UtenteBaseDTO upLoadCollabboratore(int id,UtenteBaseDTO collaboratoreDto)throws NotFoundException{
        Collaboratore collaboratore= idCollaboratore(id);
        collaboratore.setNome(collaboratoreDto.getNome());
        collaboratore.setEmail(collaboratoreDto.getEmail());
        collaboratore.setPassword(encoder.encode(collaboratoreDto.getPassword()));
        collaboratore.setComune(collaboratoreDto.getComune());
        collaboratore.setRegione(collaboratoreDto.getRegione());
        collaboratore.setCognome(collaboratoreDto.getCognome());
        collaboratoreRepository.save(collaboratore);
       collaboratoreDto.setId(collaboratore.getId());
       collaboratoreDto.setCv(collaboratore.getCv());
        return collaboratoreDto;

    }
    public void deleteCollaboratore(int id) throws NotFoundException{
        Collaboratore collaboratore = idCollaboratore(id);
        collaboratoreRepository.delete(collaboratore);
    }
    public Collaboratore caricaCv(int id,String url)throws NotFoundException{
        Collaboratore collaboratore= idCollaboratore(id);
        collaboratore.setCv(url);
        return collaboratoreRepository.save(collaboratore);
    }
    public UtenteBaseDTO convertToDTO(Collaboratore collaboratore) {
        UtenteBaseDTO collaboratoreDTO = new UtenteBaseDTO();
        collaboratoreDTO.setId(collaboratore.getId());
        collaboratoreDTO.setNome(collaboratore.getNome());
        collaboratoreDTO.setCognome(collaboratore.getCognome());
        collaboratoreDTO.setEmail(collaboratore.getEmail());
        collaboratoreDTO.setComune(collaboratore.getComune());
        collaboratoreDTO.setRegione(collaboratore.getRegione());
        collaboratoreDTO.setCv(collaboratore.getCv());

        // Altri campi da convertire

        return collaboratoreDTO;
    }

    public UtenteBaseDTO idCollaboratoreDTO(int id) {
        Collaboratore collaboratore = collaboratoreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annuncio con id=" + id + " non trovato"));
        return convertToDTO(collaboratore);
    }

}
