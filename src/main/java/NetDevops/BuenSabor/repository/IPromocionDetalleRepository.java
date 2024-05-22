package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.PromocionDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPromocionDetalleRepository extends JpaRepository<PromocionDetalle, Long> {
}
