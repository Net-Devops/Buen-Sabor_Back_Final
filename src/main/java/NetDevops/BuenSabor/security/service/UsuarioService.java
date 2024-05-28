package NetDevops.BuenSabor.security.service;

import NetDevops.BuenSabor.security.repository.IUsuarioClienteRepository;
import NetDevops.BuenSabor.security.repository.IUsuarioEmpleadoRepository;
import NetDevops.BuenSabor.security.entity.UsuarioCliente;
import NetDevops.BuenSabor.security.entity.UsuarioEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService  {

    @Autowired
    private IUsuarioClienteRepository usuarioClienteRepository;
    @Autowired
    private IUsuarioEmpleadoRepository usuarioEmpleadoRepository;


    public UsuarioCliente crearUsuarioCliente(UsuarioCliente usuario) throws Exception {
        try {
            return usuarioClienteRepository.save(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public UsuarioCliente buscarPorIdCliente(Long idUsuarioCliente) throws Exception {
        try {
            return usuarioClienteRepository.findById(idUsuarioCliente).orElse(null);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public UsuarioCliente actualizarUsuarioCliente(Long idUsuarioCliente, UsuarioCliente usuario) throws Exception {
        try {
            UsuarioCliente usuarioActual = usuarioClienteRepository.findById(idUsuarioCliente).orElse(null);
            if (usuarioActual == null) {
                return null;
            }
            usuario.setId(idUsuarioCliente);
            return usuarioClienteRepository.save(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public boolean eliminarUsuarioCliente(Long idUsuarioCliente) throws Exception {
        try {
            usuarioClienteRepository.deleteById(idUsuarioCliente);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public List<UsuarioCliente> TraerUsuariosClientes() throws Exception {
        try {
            return usuarioClienteRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public UsuarioEmpleado crearUsuarioEmpleado(UsuarioEmpleado usuario) throws Exception {
        try {
            return usuarioEmpleadoRepository.save(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public UsuarioEmpleado buscarPorIdEmpleado(Long idUsuarioEmpleado) throws Exception {
        try {
            return usuarioEmpleadoRepository.findById(idUsuarioEmpleado).orElse(null);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public UsuarioEmpleado actualizarUsuarioEmpleado(Long idUsuarioEmpleado, UsuarioEmpleado usuario) throws Exception {
        try {
            UsuarioEmpleado usuarioActual = usuarioEmpleadoRepository.findById(idUsuarioEmpleado).orElse(null);
            if (usuarioActual == null) {
                return null;
            }
            usuario.setId(idUsuarioEmpleado);
            return usuarioEmpleadoRepository.save(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public boolean eliminarUsuarioEmpleado(Long idUsuarioEmpleado) throws Exception {
        try {
            usuarioEmpleadoRepository.deleteById(idUsuarioEmpleado);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public List<UsuarioEmpleado> TraerUsuariosEmpleados() throws Exception {
        try {
            return usuarioEmpleadoRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
