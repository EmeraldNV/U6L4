package abdellah.U6L4.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table (name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_richiesta", nullable = false)
    private LocalDate dataRichiesta;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn (name = "id_viaggio")
    private Viaggio viaggio;

    @ManyToOne
    @JoinColumn (name = "id_dipendente")
    private Dipendente dipendente;

    public Prenotazione(LocalDate dataRichiesta, String note, Viaggio viaggio) {
        this.dataRichiesta = dataRichiesta;
        this.note = note;
        this.viaggio = viaggio;
    }
}
