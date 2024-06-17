package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.UsuarioCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioClienteRepository extends JpaRepository<UsuarioCliente, Long> {


}
