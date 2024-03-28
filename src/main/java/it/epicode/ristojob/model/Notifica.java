package it.epicode.ristojob.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "notifiche")
public class Notifica {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "annuncio_id")
    private Annuncio annuncio;
    @OneToOne
    @JoinColumn(name = "recensione_id")
    private Recensione recensione;
    private String testo;


}
