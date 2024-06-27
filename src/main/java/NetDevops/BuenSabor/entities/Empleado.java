package NetDevops.BuenSabor.entities;

import NetDevops.BuenSabor.enums.Rol;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.time.LocalDate;

@Entity
@Data
@Audited
public class Empleado extends Base {

    private String nombre;
    private String apellido;
    private String Telefono;
    private String email;
    private LocalDate fechaNacimiento;
    private Rol rol;
    private String imagen;


    @OneToOne
    private UsuarioEmpleado usuarioEmpleado;
    @ManyToOne
    private Sucursal sucursal;
}
