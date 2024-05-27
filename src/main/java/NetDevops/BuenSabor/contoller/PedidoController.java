package NetDevops.BuenSabor.contoller;

import NetDevops.BuenSabor.entities.Pedido;
import NetDevops.BuenSabor.service.impl.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// GET http://localhost:8080/api/pedidos/

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/")
    public ResponseEntity<?> lista() {
        try {
            return ResponseEntity.ok().body(pedidoService.traerPedidos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(pedidoService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        try {
            return ResponseEntity.ok().body(pedidoService.actualizarPedido(id,pedido));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> cargar(@RequestBody Pedido pedido) {
        try {
            return ResponseEntity.ok().body(pedidoService.crearPedido(pedido));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(pedidoService.eliminarPedido(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
