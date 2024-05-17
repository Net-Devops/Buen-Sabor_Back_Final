package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByDenominacionAndEliminadoFalse(String denominacion);
    boolean existsByIdAndEliminadoFalse(Long id);
    Set<Categoria> findAllByEliminadoFalse();
    Categoria findByIdAndEliminadoFalse(Long id);
    Set<Categoria> findByCategoriaPadreIsNotNull();

    //para traer las subcategoria
    Set<Categoria> findByCategoriaPadreIsNotNullAndEliminadoFalse();


}
