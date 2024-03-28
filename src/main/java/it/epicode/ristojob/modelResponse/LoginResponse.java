package it.epicode.ristojob.modelResponse;

import it.epicode.ristojob.model.Azienda;
import it.epicode.ristojob.model.Utente;
import lombok.Data;

@Data
public class LoginResponse {
   String  accessToken;
   UtenteBaseDTO user ;
}
