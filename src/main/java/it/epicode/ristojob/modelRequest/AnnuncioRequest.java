package it.epicode.ristojob.modelRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AnnuncioRequest {

    private Integer idAzienda;

    private LocalDate giornoInizioLavoro;

    private LocalDate giornoFineLavoro;

    private LocalTime oraInizioLavoro;

    private LocalTime OraFineLavoro;
    @NotNull(message = "inserisci titolo annuncio")
    private String Titolo;
    private String descrizione;
    @NotNull(message = "inserisci nome struttura")
    private String nomeStruttura;
    @NotNull(message = "inserisci inserisci paga percepita")
    private int paga;
    @NotNull(message = "inserisci comune")
    private String comune;
    @NotNull(message = "inserisci provincia")
    private String regione;

}
