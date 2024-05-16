package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.ArticuloInsumo;
import NetDevops.BuenSabor.entities.ArticuloManufacturado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArticuloManufacturadoRepository extends JpaRepository<ArticuloManufacturado, Long> {
    List<ArticuloManufacturado> findByEliminadoFalse();
}
