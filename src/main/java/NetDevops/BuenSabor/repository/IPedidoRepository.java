package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long>{
    List<Pedido> findBySucursal_Id(Long sucursalId);
}
