package it.epicode.ristojob.modelRequest;

import it.epicode.ristojob.model.Annuncio;
import it.epicode.ristojob.model.Notifica;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CandidaturaRequest {
    private Integer idCollaboratore;
    @NotNull(message = "nome obbligatorio")
    private String nomeCandidato;
    @NotNull(message = "cognome obbligatorio")
    private String cognomeCandidato;
    @Email(message = "email obbligatoria")
    private String email;
    private Integer idAnnuncio;
}
