package abdellah.U6L4.repositories;

import abdellah.U6L4.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DipendentiRepository extends JpaRepository<Dipendente,Long> {
    boolean existsByEmail(String email);

}
