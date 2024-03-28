package it.epicode.ristojob.repository;

import it.epicode.ristojob.model.Annuncio;
import it.epicode.ristojob.model.Azienda;
import it.epicode.ristojob.model.Notifica;
import it.epicode.ristojob.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface AnnuncioRepository extends JpaRepository<Annuncio,Integer> {
    List<Annuncio> findByAzienda(Azienda azienda);

}
