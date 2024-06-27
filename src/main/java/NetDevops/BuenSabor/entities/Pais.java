package NetDevops.BuenSabor.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import org.hibernate.envers.Audited;

@Entity
@Data
@Audited
public class Pais extends Base{
    private String nombre;
}
