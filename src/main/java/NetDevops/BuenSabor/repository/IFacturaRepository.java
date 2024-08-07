package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFacturaRepository extends JpaRepository<Factura, Long> {
}
