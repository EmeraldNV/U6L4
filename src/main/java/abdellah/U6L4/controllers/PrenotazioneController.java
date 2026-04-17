package abdellah.U6L4.controllers;

import abdellah.U6L4.entities.Dipendente;
import abdellah.U6L4.entities.Prenotazione;
import abdellah.U6L4.payload.PrenotazionePayload;
import abdellah.U6L4.services.PrenotazioniService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    private final PrenotazioniService prenotazioniService;

    public PrenotazioneController(PrenotazioniService prenotazioniService) {
        this.prenotazioniService = prenotazioniService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Prenotazione savePrenotazione(@RequestBody PrenotazionePayload body){
        return prenotazioniService.save(body);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK) // 201
    public Page<Prenotazione> getALlPrenotazioni() {
        return this.prenotazioniService.getAllPrenotazioni(0,10,"dataRichiesta");
    }
}
