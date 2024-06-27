package NetDevops.BuenSabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Audited
public class ImagenArticulo extends Base{

    private String url;
    @ManyToOne
    @JoinColumn(name = "articulo_id")
    @JsonBackReference
    private Articulo articulo;
}
