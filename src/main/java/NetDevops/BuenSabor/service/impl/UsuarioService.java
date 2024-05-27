package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.entities.UsuarioCliente;
import NetDevops.BuenSabor.entities.UsuarioEmpleado;
import NetDevops.BuenSabor.repository.IUsuarioClienteRepository;
import NetDevops.BuenSabor.repository.IUsuarioEmpleadoRepository;
import NetDevops.BuenSabor.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioClienteRepository usuarioClienteRepository;
    @Autowired
    private IUsuarioEmpleadoRepository usuarioEmpleadoRepository;

    @Override
    public UsuarioCliente crearUsuarioCliente(UsuarioCliente usuario) throws Exception {
        try {
            return usuarioClienteRepository.save(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public UsuarioCliente buscarPorIdCliente(Long idUsuarioCliente) throws Exception {
        try {
            return usuarioClienteRepository.findById(idUsuarioCliente).orElse(null);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
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

    @Override
    public boolean eliminarUsuarioCliente(Long idUsuarioCliente) throws Exception {
        try {
            usuarioClienteRepository.deleteById(idUsuarioCliente);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<UsuarioCliente> TraerUsuariosClientes() throws Exception {
        try {
            return usuarioClienteRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public UsuarioEmpleado crearUsuarioEmpleado(UsuarioEmpleado usuario) throws Exception {
        try {
            return usuarioEmpleadoRepository.save(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public UsuarioEmpleado buscarPorIdEmpleado(Long idUsuarioEmpleado) throws Exception {
        try {
            return usuarioEmpleadoRepository.findById(idUsuarioEmpleado).orElse(null);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
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

    @Override
    public boolean eliminarUsuarioEmpleado(Long idUsuarioEmpleado) throws Exception {
        try {
            usuarioEmpleadoRepository.deleteById(idUsuarioEmpleado);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<UsuarioEmpleado> TraerUsuariosEmpleados() throws Exception {
        try {
            return usuarioEmpleadoRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
