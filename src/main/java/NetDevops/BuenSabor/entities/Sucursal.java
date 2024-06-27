package NetDevops.BuenSabor.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Audited
public class Sucursal extends Base{

    private String nombre;
    private String horaApertura;
    private String horaCierre;

    @ManyToOne
    //@JsonIgnore
    private Empresa empresa;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Domicilio domicilio;
    private String imagen;


}
