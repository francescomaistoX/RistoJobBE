package it.epicode.ristojob.model;

import it.epicode.ristojob.modelResponse.UtenteBaseDTO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "candidature")
public class Candidatura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nomeCandidato;
    private String cognomeCandidato;
    private String email;
    @ManyToOne
    @JoinColumn(name = "collaoratore_id")
    private Collaboratore collaboratore;
    @ManyToOne
    @JoinColumn(name = "annuncio_id")
    private Annuncio annuncio;

}
