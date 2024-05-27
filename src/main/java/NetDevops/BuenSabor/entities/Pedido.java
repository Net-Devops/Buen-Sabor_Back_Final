package NetDevops.BuenSabor.entities;

import NetDevops.BuenSabor.enums.Estado;
import NetDevops.BuenSabor.enums.FormaPago;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Pedido extends Base{
    private LocalTime hora;
    private Double total;
    private Double  TotalCostoProduccion;
    private Estado estado;
    private FormaPago formaPago;
    private FormaPago TipoEnvio;
    private LocalDate fechaPedido;

    @ManyToOne
    private Sucursal sucursal;
    @ManyToOne
    private Domicilio domicilio;
    @ManyToOne
    private Cliente cliente;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PedidoDetalle> pedidoDetalle;
    @OneToOne
    private Factura factura;

}
