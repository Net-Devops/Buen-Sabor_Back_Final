package NetDevops.BuenSabor.entities;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArticuloInsumo extends Articulo{

    @JsonView(Views.Public.class)
    private Double precioCompra;

    private Integer stockActual;

    private Integer stockMaximo;
    @JsonView(Views.Public.class)
    private Boolean esParaElaborar;
}
