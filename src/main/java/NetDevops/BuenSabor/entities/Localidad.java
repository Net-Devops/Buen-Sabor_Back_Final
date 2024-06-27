package NetDevops.BuenSabor.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.envers.Audited;

@Entity
@Data
@Audited
public class Localidad extends Base {
    private String nombre;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Provincia provincia;
}
