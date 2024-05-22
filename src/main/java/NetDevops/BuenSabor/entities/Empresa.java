package NetDevops.BuenSabor.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Empresa extends Base{
private String nombre;
private String razonSocial;
private Long cuil;


}
