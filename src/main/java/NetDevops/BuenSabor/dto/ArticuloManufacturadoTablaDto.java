package NetDevops.BuenSabor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ArticuloManufacturadoTablaDto extends Base{
    private String codigo;
    private String denominacion;
    private String imagen;
    private Double precioVenta;
    private String descripcion;
    private Integer tiempoEstimadoCocina;
}
