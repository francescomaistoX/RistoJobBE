package it.epicode.ristojob.repository;

import it.epicode.ristojob.model.Collaboratore;
import it.epicode.ristojob.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollaboratoreRepository extends JpaRepository<Collaboratore,Integer> {
    public Optional<Collaboratore> findByEmail(String email);
}
