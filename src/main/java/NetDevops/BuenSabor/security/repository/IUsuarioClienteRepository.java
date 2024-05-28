package NetDevops.BuenSabor.security.repository;

import NetDevops.BuenSabor.security.entity.UsuarioCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioClienteRepository extends JpaRepository<UsuarioCliente, Long> {
}
