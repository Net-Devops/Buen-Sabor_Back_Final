package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.ArticuloManufacturadoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArticuloManufacturadoDetalleRepository extends JpaRepository<ArticuloManufacturadoDetalle, Long> {
    List<ArticuloManufacturadoDetalle> findByEliminadoFalse();

    List<ArticuloManufacturadoDetalle> findByArticuloManufacturado_Id(Long id);
}
