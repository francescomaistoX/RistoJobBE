package it.epicode.ristojob.modelRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CollaboratoreRequest {
    @NotNull
    private String nome;
    @Email(message = "email obbligatoria")
    private String email;
    @NotBlank(message = "password obbligatorio")
    private String password;
    @NotNull(message ="Inseriscri comune")
    private String comune;
    @NotNull(message ="Inseriscri provincia")
    private String regione;
    private String cognome;
    private String cv;


}
