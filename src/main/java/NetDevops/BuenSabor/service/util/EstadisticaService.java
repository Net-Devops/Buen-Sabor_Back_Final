package NetDevops.BuenSabor.service.util;


import NetDevops.BuenSabor.repository.IPedidoDetalleReposiroty;
import NetDevops.BuenSabor.repository.IPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EstadisticaService {

    @Autowired
    private IPedidoRepository pedidoRepository;
    @Autowired
    private IPedidoDetalleReposiroty pedidoDetalleRepository;


}
