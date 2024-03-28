package it.epicode.ristojob.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Utente implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private  String email;
    private  String password;
    @OneToMany(mappedBy = "utenteDestinatario")
    private List<Recensione> recensioniRicevute;
    @OneToMany(mappedBy = "utenteRecensore")
    private List<Recensione> recensioniLasciate;
    @OneToMany(mappedBy = "utente")
    private List<Notifica> notifiche;
    private String comune;
    private String regione;


    public Utente(String nome, String email, String password, String comune, String regione) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.comune = comune;
        this.regione = regione;
    }

    public Utente() {
    }
}
