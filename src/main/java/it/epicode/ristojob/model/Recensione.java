package it.epicode.ristojob.model;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;


@Data
@Entity
@Table(name = "recensioni")
public class Recensione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "utente_dedtinatario_id")
    private Utente utenteDestinatario;
    @ManyToOne
    @JoinColumn(name = "utente_recensore_id")
    private Utente utenteRecensore;
    private int punteggio;
    private String testo;
    private LocalDate data;
    @OneToOne(mappedBy = "recensione")
    private Notifica notifica;

}
