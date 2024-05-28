package NetDevops.BuenSabor.security.repository;

import NetDevops.BuenSabor.security.entity.UsuarioEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioEmpleadoRepository extends JpaRepository<UsuarioEmpleado, Long> {
}
