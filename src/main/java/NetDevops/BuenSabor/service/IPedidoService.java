package NetDevops.BuenSabor.service;

import NetDevops.BuenSabor.entities.Pedido;
import NetDevops.BuenSabor.entities.UsuarioEmpleado;

import java.util.List;

public interface IPedidoService {
    public Pedido crearPedido(Pedido pedido) throws Exception;
    public Pedido actualizarPedido(Long id,Pedido pedido) throws Exception;
    public Pedido buscarPorId(Long id) throws Exception;
    public boolean eliminarPedido(Long id) throws Exception;
    public List<Pedido> traerPedidos() throws Exception;
    public List<Pedido> traerPedidos2(UsuarioEmpleado usuario) throws Exception;
}
