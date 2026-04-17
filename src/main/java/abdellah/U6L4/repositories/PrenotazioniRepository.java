package abdellah.U6L4.repositories;

import abdellah.U6L4.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazione,Long> {

}
