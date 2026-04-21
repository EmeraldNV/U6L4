package abdellah.U6L4.controllers;

import abdellah.U6L4.entities.Dipendente;
import abdellah.U6L4.exceptions.ValidationException;
import abdellah.U6L4.payload.DipendentePayload;
import abdellah.U6L4.payload.LoginPayload;
import abdellah.U6L4.payload.LoginResponsePayload;
import abdellah.U6L4.payload.NewUserPayload;
import abdellah.U6L4.services.AuthService;
import abdellah.U6L4.services.DipendentiService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final DipendentiService dipendentiService;

    public AuthController(AuthService authService, DipendentiService dipendentiService) {
        this.authService = authService;
        this.dipendentiService = dipendentiService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResponsePayload login(@RequestBody LoginPayload body){
        return new LoginResponsePayload(this.authService.checkCredentialsAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserPayload register(@RequestBody @Validated DipendentePayload body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        }




        Dipendente nuovoDipendente = this.dipendentiService.save(body);
        return new NewUserPayload((nuovoDipendente.getId()));
    }
    }

