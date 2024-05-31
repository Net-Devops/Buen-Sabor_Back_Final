package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa, Long> {
    boolean existsByCuil(Long cuil);
    boolean existsByNombre(String nombre);


    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Empresa e WHERE LOWER(e.nombre) = LOWER(:nombre) AND e.id != :id")
    boolean existsByNombreAndNotId(@Param("nombre") String nombre, @Param("id") Long id);
}
