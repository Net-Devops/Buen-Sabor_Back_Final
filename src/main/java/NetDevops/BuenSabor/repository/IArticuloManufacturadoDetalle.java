package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.ArticuloInsumo;
import NetDevops.BuenSabor.entities.ArticuloManufacturadoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArticuloManufacturadoDetalle extends JpaRepository<ArticuloManufacturadoDetalle, Long> {
    List<ArticuloManufacturadoDetalle> findByEliminadoFalse();
}
