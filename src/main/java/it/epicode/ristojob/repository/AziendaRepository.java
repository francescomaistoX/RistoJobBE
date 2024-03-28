package it.epicode.ristojob.repository;

import it.epicode.ristojob.model.Annuncio;
import it.epicode.ristojob.model.Azienda;
import it.epicode.ristojob.model.Notifica;
import it.epicode.ristojob.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AziendaRepository extends JpaRepository<Azienda,Integer> {
    public Optional<Azienda> findByEmail(String email);


}
