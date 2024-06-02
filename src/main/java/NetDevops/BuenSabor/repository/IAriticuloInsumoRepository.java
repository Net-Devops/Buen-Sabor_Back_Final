package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.ArticuloInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAriticuloInsumoRepository extends JpaRepository<ArticuloInsumo, Long> {
    List<ArticuloInsumo> findByEliminadoFalse();

    boolean existsByCodigoAndEliminadoFalse(String codigo);
    boolean existsByDenominacionAndEliminadoFalse(String denominacion);
    boolean existsByIdAndEliminadoTrue(Long id);
    boolean existsByCodigoAndEliminadoTrue(String codigo);
    boolean existsByDenominacionAndEliminadoTrue(String denominacion);
    ArticuloInsumo findByCodigoAndEliminadoTrue(String codigo);
    ArticuloInsumo findByDenominacionAndEliminadoTrue(String denominacion);
    ArticuloInsumo findByIdAndEliminadoFalse(Long id);

    List<ArticuloInsumo> findByCategoriaId(Long categoriaId);

    //region Validaciones para actualizar un insumo
    boolean existsByCodigoAndEliminadoFalseAndIdNot(String codigo, Long id);
    boolean existsByDenominacionAndEliminadoFalseAndIdNot(String denominacion, Long id);
    //endregion
}
