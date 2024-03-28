package it.epicode.ristojob.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import it.epicode.ristojob.exception.BadRequestException;
import it.epicode.ristojob.exception.LoginFaultException;
import it.epicode.ristojob.model.Azienda;
import it.epicode.ristojob.model.Collaboratore;
import it.epicode.ristojob.modelRequest.AziendaRequest;
import it.epicode.ristojob.modelRequest.CollaboratoreRequest;
import it.epicode.ristojob.modelRequest.LoginRequest;
import it.epicode.ristojob.modelResponse.AnnuncioDTO;
import it.epicode.ristojob.modelResponse.LoginResponse;
import it.epicode.ristojob.modelResponse.UtenteBaseDTO;
import it.epicode.ristojob.security.JwtTools;
import it.epicode.ristojob.service.AziendaService;
import it.epicode.ristojob.service.CollaboratoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController

public class CollaboratoreAuthController {
    @Autowired
    private CollaboratoreService collaboratoreService;
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private PasswordEncoder encoder;
    @Value("${upload.dir}")
    private String uploadDir;
    @PostMapping("/collaboratore/register")
    public Collaboratore register(@RequestBody @Validated CollaboratoreRequest collaboratoreRequest ,BindingResult bindingResult) throws BadRequestException, IOException {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

       return collaboratoreService.saveCollaboratore(collaboratoreRequest);



    }
    @PostMapping("/collaboratore/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        Collaboratore collaboratore = collaboratoreService.getUtenteByEmail(loginRequest.getEmail());

        if(encoder.matches(loginRequest.getPassword(), collaboratore.getPassword())){
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setAccessToken(jwtTools.createToken(collaboratore));
            UtenteBaseDTO utenteBaseDTO = new UtenteBaseDTO();
           utenteBaseDTO.setId(collaboratore.getId());
           utenteBaseDTO.setEmail(collaboratore.getEmail());
            utenteBaseDTO.setNome(collaboratore.getNome());
            utenteBaseDTO.setCognome(collaboratore.getCognome());
            utenteBaseDTO.setCv(collaboratore.getCv());
            utenteBaseDTO.setComune(collaboratore.getComune());
            utenteBaseDTO.setRegione(collaboratore.getRegione());
            loginResponse.setUser(utenteBaseDTO);


            return loginResponse;
        }
        else {
            throw new LoginFaultException("username/password errate");
        }
    }
    @PutMapping("/collaboratore/{id}/update")
    public UtenteBaseDTO updateUtente (@PathVariable int id,@RequestBody @Validated UtenteBaseDTO collaboratoreDto ,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return collaboratoreService.upLoadCollabboratore(id,collaboratoreDto);
    }
   
    @GetMapping("/collaboratore/{id}")
    public Collaboratore collaboratoreById (@PathVariable int id){

        return collaboratoreService.idCollaboratore(id);
    }
    @GetMapping("/collaboratore/Dto/{id}")
    public UtenteBaseDTO collaboratoreDTO (@PathVariable int id){

        return collaboratoreService.idCollaboratoreDTO(id);
    }

    @DeleteMapping("/collaboratore/{id}")
    public void deleteCollaboratore(@PathVariable int id){
        collaboratoreService.deleteCollaboratore(id);
    }
    @PostMapping("/collaboratore/{id}/cv")
    public String caricaCv(@PathVariable int id, @RequestParam("cv") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Il file CV è vuoto");
        }

        // Genera un nome univoco per il file
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        String filePath = uploadDir +"/"+ fileName;

        // Crea la directory di upload se non esiste
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Salva il file sul server
        try {
            File savedFile = new File(filePath);
            file.transferTo(savedFile);
        } catch (IOException e) {
            // Stampa i dettagli dell'eccezione nel log
            e.printStackTrace();
            // O registra i dettagli dell'eccezione nei log del server

            // Rilancia l'eccezione con un messaggio significativo
            throw new RuntimeException("Errore durante il salvataggio del file: " + e.getMessage());
        }

        // Ora puoi chiamare il servizio per salvare il percorso del file nel database
        collaboratoreService.caricaCv(id, filePath);

        return filePath;
    }


    }


