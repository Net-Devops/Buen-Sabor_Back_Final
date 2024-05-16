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
@ToString
public class ImagenArticulo extends Base{

    private String url;
    @ManyToOne
    @JoinColumn(name = "articulo_id")
    private Articulo articulo;
}
