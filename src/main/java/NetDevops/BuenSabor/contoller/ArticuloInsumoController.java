package NetDevops.BuenSabor.contoller;

import NetDevops.BuenSabor.entities.ArticuloInsumo;
import NetDevops.BuenSabor.service.IArticuloInsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.support.RouterFunctionMapping;



// GET http://localhost:8080/api/articulos/insumos/
@RestController
@RequestMapping("/api/articulos/insumos")
public class ArticuloInsumoController {


    @Autowired
    private IArticuloInsumoService articuloInsumoService;



    //region CRUD Basico
    @GetMapping("/")
    public ResponseEntity<?> buscarTodos() {
        try {
            return ResponseEntity.ok(articuloInsumoService.mostrarLista());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(articuloInsumoService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> guardar(@RequestBody ArticuloInsumo articuloInsumo) {
        try {
            ArticuloInsumo insumo = articuloInsumoService.cargar(articuloInsumo);
            return ResponseEntity.ok(insumo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody ArticuloInsumo articuloInsumo) {
        try {
            return ResponseEntity.ok(articuloInsumoService.actualizar(id, articuloInsumo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(articuloInsumoService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

//endregion


}
