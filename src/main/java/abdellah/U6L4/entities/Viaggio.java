package abdellah.U6L4.entities;


import abdellah.U6L4.enums.StatoViaggio;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

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

    public Viaggio(String destinazione, LocalDate data, StatoViaggio statoViaggio) {
        this.destinazione = destinazione;
        this.data = data;
        this.statoViaggio = statoViaggio;
    }
}
