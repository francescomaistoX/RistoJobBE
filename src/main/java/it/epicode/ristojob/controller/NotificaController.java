package it.epicode.ristojob.controller;

import it.epicode.ristojob.model.Notifica;
import it.epicode.ristojob.service.NotificaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class NotificaController {
    @Autowired
    private NotificaService notificaService;
    @GetMapping("/notifica")
    public List<Notifica> getAllForIdUtente(@RequestParam int idUtente){
        return notificaService.getAllNotifiche(idUtente);

    }
    @DeleteMapping("/notifica/{id}")
    public void deleteNotifica(int idNotifica){
       notificaService.deleteNotifia(idNotifica);
    }
}
