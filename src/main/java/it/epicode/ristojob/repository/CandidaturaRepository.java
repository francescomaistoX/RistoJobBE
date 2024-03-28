package it.epicode.ristojob.repository;

import it.epicode.ristojob.model.Annuncio;
import it.epicode.ristojob.model.Candidatura;
import it.epicode.ristojob.model.Collaboratore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura,Integer> {
    List<Candidatura> findByAnnuncio(Annuncio annuncio);
    List<Candidatura> findByCollaboratore(Collaboratore collaboratore);
}
