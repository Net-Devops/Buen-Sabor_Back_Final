package NetDevops.BuenSabor.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class PedidoDetalle extends Base{
    private Integer cantidad;

    @ManyToOne
    private Pedido pedido;
    @ManyToOne
    private Articulo articulo;
}
