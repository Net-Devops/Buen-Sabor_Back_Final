package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.ArticuloInsumo;
import NetDevops.BuenSabor.entities.ArticuloManufacturado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IArticuloManufacturadoRepository extends JpaRepository<ArticuloManufacturado, Long> {
    Set<ArticuloManufacturado> findByEliminadoFalse();
    ArticuloManufacturado findByIdAndEliminadoFalse(Long id);
    boolean existsByDenominacionAndEliminadoFalse(String denominacion);
    boolean existsByCodigoAndEliminadoFalse(String codigo);

    //region Validaciones para actualizar un Manufacturado
    boolean existsByCodigoAndEliminadoFalseAndIdNot(String codigo, Long id);
    boolean existsByDenominacionAndEliminadoFalseAndIdNot(String denominacion, Long id);
    //endregion
}
