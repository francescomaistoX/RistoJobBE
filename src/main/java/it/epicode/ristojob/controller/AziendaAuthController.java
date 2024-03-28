package it.epicode.ristojob.controller;

import it.epicode.ristojob.exception.BadRequestException;
import it.epicode.ristojob.exception.LoginFaultException;
import it.epicode.ristojob.model.Azienda;
import it.epicode.ristojob.model.Collaboratore;
import it.epicode.ristojob.modelRequest.AziendaRequest;
import it.epicode.ristojob.modelRequest.CollaboratoreRequest;
import it.epicode.ristojob.modelRequest.LoginRequest;
import it.epicode.ristojob.modelResponse.LoginResponse;
import it.epicode.ristojob.modelResponse.UtenteBaseDTO;
import it.epicode.ristojob.security.JwtTools;
import it.epicode.ristojob.service.AziendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController

public class AziendaAuthController {
    @Autowired
   private AziendaService aziendaService;
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/azienda/register")
    public Azienda register(@RequestBody @Validated AziendaRequest aziendaRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return aziendaService.saveAzienda(aziendaRequest);
    }

    @PostMapping("/azienda/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

       Azienda azienda = aziendaService.getUtenteByEmail(loginRequest.getEmail());

        if(encoder.matches(loginRequest.getPassword(), azienda.getPassword())){
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setAccessToken(jwtTools.createToken(azienda));
            UtenteBaseDTO utenteBaseDTO = new UtenteBaseDTO();
            utenteBaseDTO.setId(azienda.getId());
            utenteBaseDTO.setNome(azienda.getNome());
            utenteBaseDTO.setEmail(azienda.getEmail());
            loginResponse.setUser(utenteBaseDTO);
            return loginResponse;
        }
        else {
            throw new LoginFaultException("username/password errate");
        }
    }
    @GetMapping("/azienda/{id}")
    public Azienda aziendaById(@PathVariable int id){
       
        return aziendaService.idAzienda(id);
    }
    @GetMapping("/azienda/Dto/{id}")
    public UtenteBaseDTO aziendaDTO (@PathVariable int id){

        return aziendaService.idAziendaDTO(id);
    }


    @PutMapping("/azienda/{id}/update")
    public UtenteBaseDTO updateUtente (@PathVariable int id, @RequestBody @Validated UtenteBaseDTO aziendaDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return aziendaService.upLoadAzienda(id,aziendaDto);
    }

    @DeleteMapping("/azienda/{id}")
    public void deleteAzienda(@PathVariable int id){
        aziendaService.deleteAzienda(id);
    }
}



