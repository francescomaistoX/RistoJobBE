package it.epicode.ristojob.repository;

import it.epicode.ristojob.model.Notifica;
import it.epicode.ristojob.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificaRepository extends JpaRepository<Notifica,Integer> {
    List<Notifica> findByUtente(Utente utente);
}
