package abdellah.U6L4.repositories;

import abdellah.U6L4.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ViaggiRepository extends JpaRepository<Viaggio,Long> {
    boolean existsByData(LocalDate data);
}
