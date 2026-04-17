package abdellah.U6L4.controllers;

import abdellah.U6L4.entities.Dipendente;
import abdellah.U6L4.entities.Viaggio;
import abdellah.U6L4.payload.ViaggioPayload;
import abdellah.U6L4.services.ViaggiService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    private final ViaggiService viaggiService;


    public ViaggioController(ViaggiService viaggiService) {
        this.viaggiService = viaggiService;
    }

    @PostMapping
    public Viaggio saveViaggio(@RequestBody ViaggioPayload body){
    return viaggiService.save(body);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK) // 201
    public Page<Viaggio> getAllViaggi() {
        return this.viaggiService.getAllViaggi(0,10,"destinazione");
    }
}
