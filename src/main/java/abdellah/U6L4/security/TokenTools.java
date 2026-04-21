package abdellah.U6L4.security;

import abdellah.U6L4.entities.Dipendente;
import abdellah.U6L4.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenTools {

    private final String secret;

    public TokenTools(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    public String generateToken(Dipendente dipendente){
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis())) // Data di emissione (IaT - Issued At), va messa in millisecondi
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // Data di scadenza (Expiration Date), anche questa in millisecondi
                .subject(String.valueOf(dipendente.getId())) // Subject ovvero a chi appartiene il token. Inseriamo l'id del proprietario <-- MAI METTERE DATI SENSIBILI AL SUO INTERNO
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())) // Firmiamo il token con un segreto e con un algoritmo apposito
                .compact();
    }

    public void verifyToken (String token){
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("Problemi col token! Effettua di nuovo il login!");
        }
    }

    public Long extractIdfromToken(String token){
        return Long.parseLong(Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parseSignedClaims(token).getPayload().getSubject());
    }
}


