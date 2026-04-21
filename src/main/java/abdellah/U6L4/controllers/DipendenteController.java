package abdellah.U6L4.controllers;


import abdellah.U6L4.entities.Dipendente;
import abdellah.U6L4.payload.DipendentePayload;
import abdellah.U6L4.services.DipendentiService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @ResponseStatus(HttpStatus.OK) // 201
    public Page<Dipendente> getAllDipendenti(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "surname") String sortBy) {
        return this.dipendentiService.getAllDipendenti(page,size,sortBy);
    }

    @GetMapping("/me")
    public Dipendente getOwnProfile(@AuthenticationPrincipal Dipendente currentAuthenticateDipendente){
        return  currentAuthenticateDipendente;
    }

    @PutMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOwnProfile(@AuthenticationPrincipal Dipendente currentAuthenticatedDipendente){
        this.dipendentiService.findByIdAndDelete(currentAuthenticatedDipendente.getId());
    }

    @PutMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOwnProfile(@AuthenticationPrincipal Dipendente currentAuthenticatedDipendente, @RequestBody DipendentePayload body){
        this.dipendentiService.findByIdAndUpdate(currentAuthenticatedDipendente.getId(),body);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOwnProfile(@AuthenticationPrincipal Dipendente currentAuthenticatedDipendente){

    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN')")
    public Dipendente getById(@PathVariable Long userId) {
        return this.dipendentiService.findById(userId);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN')")
    public Dipendente getByIdAndUpdate(@PathVariable Long userId, @RequestBody DipendentePayload body) {
        return this.dipendentiService.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN')")
    public void getByIdAndDelete(@PathVariable Long userId) {
        this.dipendentiService.findByIdAndDelete(userId);
    }

}
