package abdellah.U6L4.payload;

import abdellah.U6L4.entities.Dipendente;
import abdellah.U6L4.entities.Viaggio;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PrenotazionePayload {

    private LocalDate dataRichiesta;
    private String note;
    private Viaggio viaggio;
    private Dipendente dipendente;
}
