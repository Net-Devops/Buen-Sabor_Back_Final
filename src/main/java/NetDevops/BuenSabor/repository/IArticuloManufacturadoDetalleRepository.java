package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.ArticuloManufacturadoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IArticuloManufacturadoDetalleRepository extends JpaRepository<ArticuloManufacturadoDetalle, Long> {
    Set<ArticuloManufacturadoDetalle> findByEliminadoFalse();

    Set<ArticuloManufacturadoDetalle> findByArticuloManufacturado_Id(Long id);
}
