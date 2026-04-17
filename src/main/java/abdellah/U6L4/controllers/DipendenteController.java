package abdellah.U6L4.controllers;


import abdellah.U6L4.entities.Dipendente;
import abdellah.U6L4.payload.DipendentePayload;
import abdellah.U6L4.services.DipendentiService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    private final DipendentiService dipendentiService;

    public DipendenteController(DipendentiService dipendentiService) {
        this.dipendentiService = dipendentiService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente(@RequestBody DipendentePayload body) {

        return this.dipendentiService.save(body);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK) // 201
    public Page<Dipendente> getAllDipendenti() {
        return this.dipendentiService.getAllDipendenti(0,10,"cognome");
    }
}
