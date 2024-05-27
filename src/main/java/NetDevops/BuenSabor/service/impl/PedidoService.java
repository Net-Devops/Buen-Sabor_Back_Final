package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.entities.Pedido;
import NetDevops.BuenSabor.repository.IPedidoRepository;
import NetDevops.BuenSabor.service.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Pedido> traerPedidos() throws Exception{
        try {
            return pedidoRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
