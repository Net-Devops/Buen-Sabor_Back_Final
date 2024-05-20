package NetDevops.BuenSabor.entities;

import NetDevops.BuenSabor.enums.TipoPromocion;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Promocion extends Base{

    private String denominacion;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private LocalTime horaDesde;
    private LocalTime horaHasta;
    private String descripcionDescuento;
    private Double precioPromocional;
    private TipoPromocion tipoPromocion;


    @OneToMany(cascade = CascadeType.ALL)
    private Set<PromocionDetalle> promocionDetalles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<ImagenPromocion> imagenes = new HashSet<>();


//    @ManyToMany(mappedBy = "promociones")
//    private Set<Sucursal> sucursales = new HashSet<>();

}
