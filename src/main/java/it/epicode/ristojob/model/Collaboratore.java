package it.epicode.ristojob.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "collaboratori")
public class Collaboratore extends Utente{
    @OneToMany(mappedBy = "collaboratore",cascade = CascadeType.REMOVE)
    private List<Candidatura> candidature;
    private String cognome;
    private String cv ;

    public Collaboratore(String cognome, String cv,String nome,String email,String password,String comune,String regione) {
        super(nome,email,password,comune,regione);
        this.cognome = cognome;
        this.cv = cv;
    }

    public Collaboratore() {
        super();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
