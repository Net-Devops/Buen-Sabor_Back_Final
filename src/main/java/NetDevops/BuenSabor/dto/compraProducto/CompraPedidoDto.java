package NetDevops.BuenSabor.dto.compraProducto;

import NetDevops.BuenSabor.dto.BaseDto;
import NetDevops.BuenSabor.entities.*;
import lombok.Data;

import java.util.List;

@Data
public class CompraPedidoDto extends BaseDto {
    private String hora;
    private Double total;
    private Double totalCosto;
    private String fechaPedido;
    private String preferenceMPId;
    private Sucursal sucursal;
    private Domicilio domicilio;
    private Cliente cliente;
    private List<PedidoDetalleDto> pedidoDetalle;
    private Factura factura;
}