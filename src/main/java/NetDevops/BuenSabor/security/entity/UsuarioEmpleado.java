package NetDevops.BuenSabor.security.entity;

import NetDevops.BuenSabor.entities.Base;
import NetDevops.BuenSabor.entities.Empleado;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class UsuarioEmpleado extends EntityManager{
    private String username;
    private String password;

    private Empleado empleado;
}
