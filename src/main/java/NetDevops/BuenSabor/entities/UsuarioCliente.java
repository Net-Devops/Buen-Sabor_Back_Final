package NetDevops.BuenSabor.entities;

import jakarta.persistence.Entity;
import lombok.Data;

import javax.management.relation.Role;

@Entity
@Data
public class UsuarioCliente extends Base{
    private String username;
    private String password;

}
