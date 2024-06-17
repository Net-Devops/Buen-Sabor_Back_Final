package NetDevops.BuenSabor.dto;

import NetDevops.BuenSabor.enums.Rol;
import lombok.Data;

import javax.management.relation.Role;


@Data
public class UserResponseDto {
    private String username;
    private Rol role;
    private Long idUsuario;
}
