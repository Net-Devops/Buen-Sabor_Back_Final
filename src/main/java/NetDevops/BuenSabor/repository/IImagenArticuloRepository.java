package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.ArticuloInsumo;
import NetDevops.BuenSabor.entities.ImagenArticulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImagenArticuloRepository extends JpaRepository<ImagenArticulo, Long> {
    List<ImagenArticulo> findByEliminadoFalse();
}
