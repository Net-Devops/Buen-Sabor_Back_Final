package NetDevops.BuenSabor.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
//@Audited
public class PromocionDetalle extends Base{
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "articulo_id")
    private Articulo articulo;
    @ManyToOne
    @JoinColumn(name = "promocion_id")
    private Promocion promocion;
    @ManyToOne
    @JoinColumn(name = "imagen_promocion_id")
    private ImagenPromocion imagenPromocion;
}
