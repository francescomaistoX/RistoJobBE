package it.epicode.ristojob.modelResponse;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecensioniDTO {
    private int id;
    private String testo;
    private LocalDate data;
    private String utenteRecensore;
    private Integer UtenteRecensito;
}
