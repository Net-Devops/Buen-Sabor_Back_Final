package NetDevops.BuenSabor.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
public class Sucursal extends Base{

    private String nombre;
    private LocalTime horaApertura;
    private LocalTime horaCierre;

    @ManyToOne
    //@JsonIgnore
    private Empresa empresa;

    //private Domicilio domicilio;

}
