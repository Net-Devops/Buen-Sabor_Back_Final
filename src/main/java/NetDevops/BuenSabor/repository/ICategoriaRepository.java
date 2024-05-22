package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByDenominacionAndEliminadoFalse(String denominacion);
    boolean existsByIdAndEliminadoFalse(Long id);
    Set<Categoria> findAllByEliminadoFalse();
    Categoria findByIdAndEliminadoFalse(Long id);
    Set<Categoria> findByCategoriaPadreIsNotNull();
    Set<Categoria> findByCategoriaPadre_IdAndEliminadoFalse(Long categoriaPadreId);
    //para traer las subcategoria
    Set<Categoria> findByCategoriaPadreIsNotNullAndEliminadoFalse();


    @Query("SELECT c FROM Categoria c WHERE c.categoriaPadre IS NULL OR c.categoriaPadre.id = 0")
    Set<Categoria> ListaCategorias();


}
