package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.ArticuloInsumo;
import NetDevops.BuenSabor.entities.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUnidadMedidaRepository extends JpaRepository<UnidadMedida, Long> {
    List<UnidadMedida> findByEliminadoFalse();
}
