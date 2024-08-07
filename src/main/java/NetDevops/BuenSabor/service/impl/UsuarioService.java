package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.dto.usuario.UserResponseDto;
import NetDevops.BuenSabor.entities.Cliente;
import NetDevops.BuenSabor.entities.Empleado;
import NetDevops.BuenSabor.entities.UsuarioCliente;
import NetDevops.BuenSabor.entities.UsuarioEmpleado;
import NetDevops.BuenSabor.repository.IClienteRepository;
import NetDevops.BuenSabor.repository.IEmpleadoRepository;
import NetDevops.BuenSabor.repository.IUsuarioClienteRepository;
import NetDevops.BuenSabor.repository.IUsuarioEmpleadoRepository;
import NetDevops.BuenSabor.service.IUsuarioService;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioClienteRepository usuarioClienteRepository;
    @Autowired
    private IUsuarioEmpleadoRepository usuarioEmpleadoRepository;
    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    private IEmpleadoRepository empleadoRepository;

    @Override
    public UsuarioCliente crearUsuarioCliente(UsuarioCliente usuario) throws Exception {
        try {

            String plainPassword = usuario.getPassword();
            String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
            usuario.setPassword(hashedPassword);
            return usuarioClienteRepository.save(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
   public UserResponseDto loginCliente(String username, String password) throws InvalidCredentialsException {
    UsuarioCliente usuario = usuarioClienteRepository.findByUsername(username);
    if (usuario != null && BCrypt.checkpw(password, usuario.getPassword())) {
        Cliente cliente = clienteRepository.findByUsuarioCliente_Id(usuario.getId());
        UserResponseDto userResponse = new UserResponseDto();
        userResponse.setUsername(usuario.getUsername());
        userResponse.setRole(cliente.getRol());
        userResponse.setIdUsuario(cliente.getId());

        return userResponse;
    } else {
        throw new InvalidCredentialsException("Invalid username or password");
    }
}

    @Override
    public UsuarioEmpleado crearUsuarioEmpleado(UsuarioEmpleado usuario) throws Exception {
        try {
            String plainPassword = usuario.getPassword();
            String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
            usuario.setPassword(hashedPassword);
            return usuarioEmpleadoRepository.save(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public UserResponseDto loginEmpleado(String username, String password) throws InvalidCredentialsException {
        UsuarioCliente usuario = usuarioClienteRepository.findByUsername(username);
        if (usuario != null && BCrypt.checkpw(password, usuario.getPassword())) {
            Empleado empleado = empleadoRepository.findByUsuarioEmpleado_Id(usuario.getId());
            UserResponseDto userResponse = new UserResponseDto();
            userResponse.setUsername(usuario.getUsername());
            userResponse.setRole(empleado.getRol());
            userResponse.setIdUsuario(empleado.getId());

            return userResponse;
        } else {
            throw new InvalidCredentialsException("Invalid username or password");
        }
    }

}
