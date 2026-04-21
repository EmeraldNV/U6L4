package abdellah.U6L4.services;

import abdellah.U6L4.entities.Dipendente;
import abdellah.U6L4.exceptions.NotFoundException;
import abdellah.U6L4.exceptions.UnauthorizedException;
import abdellah.U6L4.payload.LoginPayload;
import abdellah.U6L4.security.TokenTools;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {



    private final DipendentiService dipendentiService;
    private final TokenTools tokenTools;
    private final PasswordEncoder bcrypt;

    public AuthService(DipendentiService dipendentiService, TokenTools tokenTools, PasswordEncoder bcrypt) {
        this.dipendentiService = dipendentiService;
        this.tokenTools = tokenTools;
        this.bcrypt = bcrypt;
    }


    public String checkCredentialsAndGenerateToken(LoginPayload body) {
    		try {
        Dipendente found = this.dipendentiService.findByEmail(body.getEmail());
        // TODO: Migliorare gestione password
        if (this.bcrypt.matches(body.getPassword(),found.getPassword())) {
            return this.tokenTools.generateToken(found);
        } else {
            throw new UnauthorizedException("Credenziali errate");
        }
    } catch (
    NotFoundException ex) {
        throw new UnauthorizedException("Credenziali errate");
    }
    }
}
