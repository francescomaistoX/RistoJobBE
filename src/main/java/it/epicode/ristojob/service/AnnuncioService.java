package it.epicode.ristojob.service;

import it.epicode.ristojob.exception.BadRequestException;
import it.epicode.ristojob.exception.NotFoundException;
import it.epicode.ristojob.model.Annuncio;
import it.epicode.ristojob.model.Azienda;
import it.epicode.ristojob.modelRequest.AnnuncioRequest;
import it.epicode.ristojob.modelResponse.AnnuncioDTO;
import it.epicode.ristojob.repository.AnnuncioRepository;
import it.epicode.ristojob.repository.AziendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnuncioService {
    @Autowired
    private AnnuncioRepository annuncioRepository;
    @Autowired
    private AziendaRepository aziendaRepository;
    @Autowired
    private AziendaService aziendaService;

    public AnnuncioDTO saveAnnuncio(AnnuncioDTO annuncioDTO, int idAzienda) throws BadRequestException {
        Annuncio annuncio = new Annuncio();
        Azienda azienda = aziendaService.idAzienda(idAzienda);
        annuncio.setAzienda(azienda);
        annuncio.setTitolo(annuncioDTO.getTitolo());
        annuncio.setNomeStruttura(annuncioDTO.getNomeStruttura());
        annuncio.setGiornoInizioLavoro(annuncioDTO.getGiornoInizioLavoro());
        annuncio.setGiornoFineLavoro(annuncioDTO.getGiornoFineLavoro());
        annuncio.setOraInizioLavoro(annuncioDTO.getOraInizioLavoro());
        annuncio.setOraFineLavoro(annuncioDTO.getOraFineLavoro());
        annuncio.setDescrizione(annuncioDTO.getDescrizione());
        annuncio.setPaga(annuncioDTO.getPaga());
        annuncio.setComune(annuncioDTO.getComune());
        annuncio.setRegione(annuncioDTO.getRegione());
        azienda.getAnnunci().add(annuncio);
        aziendaRepository.save(azienda);
        annuncioRepository.save(annuncio);
        annuncioDTO.setIdAzienda(azienda.getId());
        annuncioDTO.setId(annuncio.getId());
        return annuncioDTO;
    }
    public Annuncio idAnnuncio(int id) {
        return annuncioRepository.findById(id).orElseThrow(()->new NotFoundException("annuncio con id="+ id + " non trovato"));

    }
    public AnnuncioDTO convertToDTO(Annuncio annuncio) {
        AnnuncioDTO annuncioDTO = new AnnuncioDTO();
        annuncioDTO.setId(annuncio.getId());
        annuncioDTO.setTitolo(annuncio.getTitolo());
        annuncioDTO.setNomeStruttura((annuncio.getNomeStruttura()));
        annuncioDTO.setDescrizione(annuncio.getDescrizione());
        annuncioDTO.setGiornoInizioLavoro(annuncio.getGiornoInizioLavoro());
        annuncioDTO.setGiornoFineLavoro(annuncio.getGiornoFineLavoro());
        annuncioDTO.setOraInizioLavoro(annuncio.getOraInizioLavoro());
        annuncioDTO.setOraFineLavoro(annuncio.getOraFineLavoro());
        annuncioDTO.setPaga(annuncio.getPaga());
        annuncioDTO.setComune(annuncio.getComune());
        annuncioDTO.setRegione(annuncio.getRegione());
        annuncioDTO.setIdAzienda(annuncio.getAzienda().getId());
        // Altri campi da convertire

        return annuncioDTO;
    }

    public AnnuncioDTO idAnnuncioDto(int id) {
        Annuncio annuncio = annuncioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annuncio con id=" + id + " non trovato"));
        return convertToDTO(annuncio);
    }
    public AnnuncioDTO upLoadAnnuncio(AnnuncioDTO annuncioDTO,int idAnnuncio,int idAzienda)throws NotFoundException {
        Annuncio annuncio = idAnnuncio(idAnnuncio);
        Azienda azienda = aziendaService.idAzienda(idAzienda);
        annuncio.setAzienda(azienda);
        annuncio.setTitolo(annuncioDTO.getTitolo());
        annuncio.setNomeStruttura(annuncioDTO.getNomeStruttura());
        annuncio.setGiornoInizioLavoro(annuncioDTO.getGiornoInizioLavoro());
        annuncio.setGiornoFineLavoro(annuncioDTO.getGiornoFineLavoro());
        annuncio.setOraInizioLavoro(annuncioDTO.getOraInizioLavoro());
        annuncio.setOraFineLavoro(annuncioDTO.getOraFineLavoro());
        annuncio.setDescrizione(annuncioDTO.getDescrizione());
        annuncio.setPaga(annuncioDTO.getPaga());
        annuncio.setComune(annuncioDTO.getComune());
        annuncio.setRegione(annuncioDTO.getRegione());
        azienda.getAnnunci().add(annuncio);
        aziendaRepository.save(azienda);
        annuncioRepository.save(annuncio);
        return annuncioDTO;

    }
    public List<AnnuncioDTO> getAllAnnunci(int idAzienda) {
        Azienda azienda = aziendaService.idAzienda(idAzienda);
        List<Annuncio> annunci = annuncioRepository.findByAzienda(azienda);
        List<AnnuncioDTO> dtoAnnunci = new ArrayList<>();

        for (Annuncio annuncio : annunci) {
            AnnuncioDTO dto = new AnnuncioDTO();
            dto.setId(annuncio.getId());
            dto.setTitolo(annuncio.getTitolo());
            dto.setNomeStruttura(annuncio.getNomeStruttura());
            dto.setGiornoInizioLavoro(annuncio.getGiornoInizioLavoro());
            dto.setGiornoFineLavoro(annuncio.getGiornoFineLavoro());
            dto.setOraInizioLavoro(annuncio.getOraInizioLavoro());
            dto.setOraFineLavoro(annuncio.getOraFineLavoro());
            dto.setDescrizione(annuncio.getDescrizione());
            dto.setPaga(annuncio.getPaga());
            dto.setComune(annuncio.getComune());
            dto.setRegione(annuncio.getRegione());

            dtoAnnunci.add(dto);
        }

        return dtoAnnunci;
    }
    public List<AnnuncioDTO> getAllAnnunci() {
        List<Annuncio> annunci = annuncioRepository.findAll();
        List<AnnuncioDTO> dtoAnnunci = new ArrayList<>();

        for (Annuncio annuncio : annunci) {
            AnnuncioDTO dto = new AnnuncioDTO();
            dto.setId(annuncio.getId());
            dto.setTitolo(annuncio.getTitolo());
            dto.setNomeStruttura(annuncio.getNomeStruttura());
            dto.setGiornoInizioLavoro(annuncio.getGiornoInizioLavoro());
            dto.setGiornoFineLavoro(annuncio.getGiornoFineLavoro());
            dto.setOraInizioLavoro(annuncio.getOraInizioLavoro());
            dto.setOraFineLavoro(annuncio.getOraFineLavoro());
            dto.setDescrizione(annuncio.getDescrizione());
            dto.setPaga(annuncio.getPaga());
            dto.setComune(annuncio.getComune());
            dto.setRegione(annuncio.getRegione());

            dtoAnnunci.add(dto);
        }

        return dtoAnnunci;
    }
    public void deleteAnnuncio(int id)throws NotFoundException{
        Annuncio annuncio = idAnnuncio(id);
        annuncioRepository.delete(annuncio);
    }
}
