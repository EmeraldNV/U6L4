package abdellah.U6L4.entities;


import abdellah.U6L4.enums.StatoViaggio;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

import static abdellah.U6L4.enums.StatoViaggio.COMPLETATO;
import static abdellah.U6L4.enums.StatoViaggio.IN_PROGRAMMA;

@Entity


@NoArgsConstructor
@Getter
@Setter
@ToString
@Table (name = "viaggi")
public class Viaggio {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "destinazione", nullable = false)
    private String destinazione;

    @Column(name = "data",nullable = false)
    private LocalDate data;

    @Column(name = "stato_viaggio",nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoViaggio statoViaggio;

    public Viaggio(String destinazione, LocalDate data) {
        this.destinazione = destinazione;
        this.data = data;
        if (data.isBefore(LocalDate.now())) {
            this.statoViaggio = COMPLETATO;
        } else {
            this.statoViaggio =IN_PROGRAMMA;
        }
    }
}
