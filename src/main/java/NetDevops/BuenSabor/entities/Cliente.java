package NetDevops.BuenSabor.entities;

import NetDevops.BuenSabor.security.enums.Rol;
import NetDevops.BuenSabor.security.entity.UsuarioCliente;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Cliente extends Base{

    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;
    private Rol rol = Rol.CLIENTE;


    @ManyToMany
    private Domicilio domicilio;

    @OneToOne
    private ImagenCliente imagenCliente;
    @OneToOne
    private UsuarioCliente usuarioCliente;

}
