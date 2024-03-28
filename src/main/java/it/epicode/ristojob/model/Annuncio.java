package it.epicode.ristojob.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Data
@Entity
@Table(name = "annunci")
public class Annuncio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;
    @ManyToOne
    @JoinColumn(name = "azienda_id")
    private Azienda azienda;
    private LocalDate giornoInizioLavoro;
    private LocalDate giornoFineLavoro;
    private LocalTime oraInizioLavoro;
    private LocalTime oraFineLavoro;
    private String Titolo;
    private String descrizione;
    private String nomeStruttura;
    @OneToMany(mappedBy = "annuncio",cascade = CascadeType.REMOVE)
    private List<Notifica> notifiche;
    @OneToMany(mappedBy = "annuncio",cascade = CascadeType.REMOVE)
    private List<Candidatura> candidature;
    private int numeroCandidati;
    private int paga;
    private String comune;
    private String regione;
}
