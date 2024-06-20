package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.Categoria;
import NetDevops.BuenSabor.entities.Sucursal;
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
    Set<Categoria> findByCategoriaPadre_Id(Long categoriaPadreId);
    //para traer las subcategoria
    Set<Categoria> findByCategoriaPadreIsNotNullAndEliminadoFalse();
    List<Categoria> findAllByEmpresaId(Long idEmpresa);

    @Query("SELECT c FROM Categoria c WHERE c.categoriaPadre IS NULL OR c.categoriaPadre.id = 0")
    Set<Categoria> ListaCategorias();

    Set<Categoria> findByCategoriaPadreIsNull();
    boolean existsByCategoriaPadre_IdAndEliminadoFalse(Long categoriaPadreId);

    Set<Categoria> findBySucursales_Id(Long sucursalId);
    Set<Categoria> findByCategoriaPadre_IdAndSucursales_Id(Long categoriaPadreId, Long sucursalId);
    Set<Categoria> findBySucursalesNotContains(Sucursal sucursal);
    Set<Categoria> findByCategoriaPadre_IdAndSucursalesNotContains(Long categoriaPadreId, Sucursal sucursal);




}
