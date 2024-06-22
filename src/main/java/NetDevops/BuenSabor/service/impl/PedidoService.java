package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.entities.Pedido;
import NetDevops.BuenSabor.entities.UsuarioEmpleado;
import NetDevops.BuenSabor.enums.Rol;
import NetDevops.BuenSabor.repository.IPedidoRepository;
import NetDevops.BuenSabor.service.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService implements IPedidoService {
    @Autowired
    private IPedidoRepository pedidoRepository;


    @Override
    public Pedido crearPedido(Pedido pedido) throws Exception {
        try {
            return pedidoRepository.save(pedido);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Pedido actualizarPedido(Long id,Pedido pedido) throws Exception {
        try {
            Pedido pedidoActual = pedidoRepository.findById(id).orElse(null);
            if (pedidoActual == null) {
                return null;
            }
            pedido.setId(id);
            return pedidoRepository.save(pedido);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Pedido buscarPorId(Long id) throws Exception {
        try {
            return pedidoRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }

    @Override
    public boolean eliminarPedido(Long id) throws Exception {
        try {
            pedidoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

   @Override
public List<Pedido> traerPedidos(Long sucursalId) throws Exception{
    try {
        return pedidoRepository.findBySucursal_Id(sucursalId);
    } catch (Exception e) {
        throw new Exception(e.getMessage());
    }
}

    @Override
public List<Pedido> traerPedidos2(UsuarioEmpleado usuario) throws Exception{
    try {
        Rol rolActual = usuario.getEmpleado().getRol();
        List<Pedido> todosLosPedidos = pedidoRepository.findAll();

        switch (rolActual) {
            case ADMINISTRADOR:
                // El administrador puede ver todos los pedidos
                return todosLosPedidos;

            case EMPLEADO_CAJA:
                // El empleado de caja puede ver solo los pedidos de su sucursal
                return todosLosPedidos.stream()
                    .filter(pedido -> pedido.getSucursal().equals(usuario.getEmpleado().getSucursal()))
                    .collect(Collectors.toList());

            case EMPLEADO_REPARTIDOR:
                // El repartidor puede ver solo los pedidos de su empresa
                return todosLosPedidos.stream()
                    .filter(pedido -> pedido.getSucursal().getEmpresa().equals(usuario.getEmpleado().getSucursal().getEmpresa()))
                    .collect(Collectors.toList());

            default:
                // Otros roles no pueden ver ningún pedido
                return new ArrayList<>();
        }
    } catch (Exception e) {
        throw new Exception(e.getMessage());
    }
}
}
