package abdellah.U6L4.payload;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class DipendentePayload {

    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String password;
}
