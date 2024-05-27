package NetDevops.BuenSabor.service;

import NetDevops.BuenSabor.entities.UsuarioCliente;
import NetDevops.BuenSabor.entities.UsuarioEmpleado;

import java.util.List;

public interface IUsuarioService {
    public UsuarioCliente crearUsuarioCliente(UsuarioCliente usuario) throws Exception;
    public UsuarioCliente buscarPorIdCliente(Long idUsuarioCliente) throws Exception;
    public UsuarioCliente actualizarUsuarioCliente(Long idUsuarioCliente,UsuarioCliente usuario) throws Exception;
    public boolean eliminarUsuarioCliente(Long idUsuarioCliente) throws Exception;
    public List<UsuarioCliente> TraerUsuariosClientes() throws Exception;

    public UsuarioEmpleado crearUsuarioEmpleado(UsuarioEmpleado usuario) throws Exception;
    public UsuarioEmpleado buscarPorIdEmpleado(Long idUsuarioEmpleado) throws Exception;
    public UsuarioEmpleado actualizarUsuarioEmpleado(Long idUsuarioEmpleado,UsuarioEmpleado usuario) throws Exception;
    public boolean eliminarUsuarioEmpleado(Long idUsuarioEmpleado) throws Exception;
    public List<UsuarioEmpleado> TraerUsuariosEmpleados() throws Exception;
}
