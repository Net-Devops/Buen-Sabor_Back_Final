package NetDevops.BuenSabor.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
@SuperBuilder
//@Audited
public abstract class Articulo extends Base{

    protected String denominacion;
    protected String descripcion;
    protected String codigo;
    protected Double precioVenta;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "articulo")
    protected Set<ImagenArticulo> imagenes = new HashSet<>();
    @ManyToOne
    protected UnidadMedida unidadMedida;

}
