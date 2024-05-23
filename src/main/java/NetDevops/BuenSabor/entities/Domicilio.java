package NetDevops.BuenSabor.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Domicilio extends Base{
    private String calle;
    private Integer numero;
    private Integer cp;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Localidad localidad;
}
