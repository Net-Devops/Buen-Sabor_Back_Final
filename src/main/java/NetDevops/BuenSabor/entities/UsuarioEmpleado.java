package NetDevops.BuenSabor.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import org.hibernate.envers.Audited;

@Entity
@Data
@Audited
public class UsuarioEmpleado extends Base{
    private String username;
    private String password;

    private Empleado empleado;
}
