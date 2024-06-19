package NetDevops.BuenSabor.entities;

import NetDevops.BuenSabor.enums.TipoPromocion;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaDesde;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaHasta;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaDesde;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaHasta;
    private String descripcionDescuento;
    private Double precioPromocional;
    private TipoPromocion tipoPromocion;


    @OneToMany(cascade = CascadeType.ALL)
    private Set<PromocionDetalle> promocionDetalles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<ImagenPromocion> imagenes = new HashSet<>();


   @ManyToMany(mappedBy = "promociones")
    private Set<Sucursal> sucursales = new HashSet<>();

}
