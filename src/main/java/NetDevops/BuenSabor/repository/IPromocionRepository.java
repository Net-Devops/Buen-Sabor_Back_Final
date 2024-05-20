package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPromocionRepository extends JpaRepository<Promocion, Long> {
}
