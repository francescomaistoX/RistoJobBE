package it.epicode.ristojob.modelResponse;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CandidaturaDTO {
    private int id;
    private Integer idCollaboratore;
    private String nomeCandidato;
    private String cognomeCandidato;
    private String email;
    private Integer idAnnuncio;
}
