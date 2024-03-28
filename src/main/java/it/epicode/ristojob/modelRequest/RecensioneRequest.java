package it.epicode.ristojob.modelRequest;

import it.epicode.ristojob.model.Utente;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RecensioneRequest {
    private int punteggio;
    @NotNull(message = "compila il corpo della recensione")
    private String testo;
    private LocalDate data;
    private Integer utenteRecensore;
    private Integer UtenteRecensito;
    private Integer notifica;
}

