package abdellah.U6L4.repositories;

import abdellah.U6L4.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazione,Long> {
    boolean existsByDataRichiesta(LocalDate dataRichiesta);
}
