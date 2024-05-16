package NetDevops.BuenSabor.contoller;

import NetDevops.BuenSabor.entities.ArticuloManufacturado;
import NetDevops.BuenSabor.service.IArticuloManufacturadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articulos/manufacturados")
public class ArticuloManufacturadoController {
    @Autowired
    private IArticuloManufacturadoService articuloManufacturadoService;


    //region CRUD Basico
    @GetMapping("/")
    public ResponseEntity<?> Lista() {
        try {
            return ResponseEntity.status(200).body(articuloManufacturadoService.listaArticuloManufacturado());
        }catch (Exception e) {
            return ResponseEntity.status(500).body("{\"error\":\"Error\"}");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(200).body(articuloManufacturadoService.buscarPorId(id));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("{\"error\":\"Error\"}");
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> cargarArticuloManufacturado(@RequestBody ArticuloManufacturado articuloManufacturado) {
        try {
            return ResponseEntity.status(200).body(articuloManufacturadoService.cargarArticuloManufacturado(articuloManufacturado));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("{\"error\":\"Error\"}");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarArticuloManufacturado(@PathVariable Long id, @RequestBody ArticuloManufacturado articuloManufacturado) {
        try {
            return ResponseEntity.status(200).body(articuloManufacturadoService.actualizarArticuloManufacturado(id, articuloManufacturado));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("{\"error\":\"Error\"}");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarArticuloManufacturado(@PathVariable Long id) {
        try {
            return ResponseEntity.status(204).body(articuloManufacturadoService.eliminarArticuloManufacturado(id));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("{\"error\":\"Error\"}");
        }
    }
    //endregion

}
