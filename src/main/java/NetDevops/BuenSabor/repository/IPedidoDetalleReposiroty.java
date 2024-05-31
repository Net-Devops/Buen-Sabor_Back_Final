package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.PedidoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPedidoDetalleReposiroty extends JpaRepository<PedidoDetalle, Long> {
}
