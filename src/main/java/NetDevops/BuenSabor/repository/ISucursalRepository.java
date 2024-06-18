package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISucursalRepository extends JpaRepository<Sucursal, Long> {
    List<Sucursal> findByEmpresaIdAndEliminadoFalse(Long empresaId);
    List<Sucursal> findByEmpresaId(Long empresaId);
    boolean findByNombre(String nombre);
    boolean existsByIdAndEliminadoFalse(long id);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Sucursal s WHERE LOWER(s.nombre) = LOWER(:nombre) AND s.id != :id")
    boolean existsByNombreAndNotId(@Param("nombre") String nombre, @Param("id") Long id);

}
