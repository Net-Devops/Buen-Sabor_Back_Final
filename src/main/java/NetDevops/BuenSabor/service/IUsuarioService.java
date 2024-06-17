package NetDevops.BuenSabor.service;

import NetDevops.BuenSabor.dto.UserResponseDto;
import NetDevops.BuenSabor.entities.UsuarioCliente;
import NetDevops.BuenSabor.entities.UsuarioEmpleado;
import org.apache.http.auth.InvalidCredentialsException;

import java.util.List;

public interface IUsuarioService {
    public UsuarioCliente crearUsuarioCliente(UsuarioCliente usuario) throws Exception;
    public UserResponseDto loginCliente(String username, String password) throws InvalidCredentialsException;



    public UsuarioEmpleado crearUsuarioEmpleado(UsuarioEmpleado usuario) throws Exception;
    public UserResponseDto loginEmpleado(String username, String password) throws InvalidCredentialsException;
}
