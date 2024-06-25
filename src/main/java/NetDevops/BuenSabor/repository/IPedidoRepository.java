package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.Cliente;
import NetDevops.BuenSabor.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long>{
    List<Pedido> findBySucursal_Id(Long sucursalId);

}
