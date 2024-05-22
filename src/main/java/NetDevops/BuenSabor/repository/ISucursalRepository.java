package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISucursalRepository extends JpaRepository<Sucursal, Long> {
    List<Sucursal> findByEmpresaIdAndEliminadoFalse(Long empresaId);
}
