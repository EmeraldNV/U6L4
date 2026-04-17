package abdellah.U6L4.payload;

import abdellah.U6L4.enums.StatoViaggio;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ViaggioPayload {

    private String destinazione;
    private LocalDate data;
    private StatoViaggio statoViaggio;
}
