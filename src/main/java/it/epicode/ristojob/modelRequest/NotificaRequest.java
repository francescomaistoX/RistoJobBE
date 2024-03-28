package it.epicode.ristojob.modelRequest;

import lombok.Data;

@Data
public class NotificaRequest {
    private Integer idDestinatario;
    private Integer idAnnuncio;
    private Integer idRecensione;
    private String testo;

}
