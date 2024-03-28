package it.epicode.ristojob.modelResponse;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UtenteBaseDTO {
    int id;
    String nome;
    String cognome;
    String email;
    String comune;
    String regione;
    @NotNull
    String password;
    String cv;


}
