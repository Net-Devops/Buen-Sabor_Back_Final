package NetDevops.BuenSabor.contoller;

import NetDevops.BuenSabor.service.impl.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/local")
public class LocalController {
    @Autowired
    private LocalService localService;

    @PostMapping("/agregarSucursalACategoria/{categoriaId}/{sucursalId}")
    public ResponseEntity<?> agregarSucursalACategoria(@PathVariable Long categoriaId, @PathVariable Long sucursalId){
        try {
            return ResponseEntity.ok(localService.agregarSucursalACategoria(categoriaId, sucursalId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/desasociarSucursalDeCategoria/{categoriaId}/{sucursalId}")
    public ResponseEntity<?> desasociarSucursalDeCategoria(@PathVariable Long categoriaId, @PathVariable Long sucursalId){
        try {
            return ResponseEntity.ok(localService.desasociarSucursalDeCategoria(categoriaId, sucursalId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/traerTodo/{sucursalId}")
    public ResponseEntity<?> traerTodo(@PathVariable Long sucursalId){
        try {
            return ResponseEntity.ok(localService.traerTodo(sucursalId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/traerCategoriasNoAsociadasASucursal/{sucursalId}")
    public ResponseEntity<?> traerCategoriasNoAsociadasASucursal(@PathVariable Long sucursalId){
        try {
            return ResponseEntity.ok(localService.traerCategoriasNoAsociadasASucursal(sucursalId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}