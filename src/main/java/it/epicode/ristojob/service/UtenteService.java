package it.epicode.ristojob.service;


import it.epicode.ristojob.exception.NotFoundException;
import it.epicode.ristojob.model.Utente;
import it.epicode.ristojob.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UtenteRepository utenteRepository;




          public Utente getUtenteByEmail(String email){
            return utenteRepository.findByEmail(email).orElseThrow(()->new NotFoundException("Utente non trovato"));
        }
}


