package NetDevops.BuenSabor.security.entity;

import NetDevops.BuenSabor.entities.Base;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class UsuarioCliente extends EntityManager {
    private String username;
    private String password;

}
