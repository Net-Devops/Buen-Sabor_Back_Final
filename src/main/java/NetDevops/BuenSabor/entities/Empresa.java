package NetDevops.BuenSabor.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.util.Set;

@Entity
@Data
@Audited
public class Empresa extends Base{
private String nombre;
private String razonSocial;
private Long cuil;
private String imagen;




}
