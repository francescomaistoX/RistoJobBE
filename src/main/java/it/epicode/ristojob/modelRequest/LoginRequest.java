package it.epicode.ristojob.modelRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @Email(message = "Email obbligatoria")
    private String email;
    @NotBlank(message = "password obbligatorio")
    private String password;
}
