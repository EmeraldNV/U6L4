package abdellah.U6L4.payload;

import abdellah.U6L4.enums.StatoViaggio;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ViaggioPayload {

    private String destinazione;
    private LocalDate data;

}
