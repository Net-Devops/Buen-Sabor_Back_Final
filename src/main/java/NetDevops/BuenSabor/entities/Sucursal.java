package NetDevops.BuenSabor.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
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
