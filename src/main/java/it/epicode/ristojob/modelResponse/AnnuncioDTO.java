package it.epicode.ristojob.modelResponse;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AnnuncioDTO {
    private int id;
    private int idAzienda;
    private LocalDate giornoInizioLavoro;
    private LocalDate giornoFineLavoro;
    private LocalTime oraInizioLavoro;
    private LocalTime OraFineLavoro;
    private String Titolo;
    private String descrizione;
    private String nomeStruttura;
    private int paga;
    private String comune;
    private String regione;
}
