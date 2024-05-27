package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, Long> {
}
