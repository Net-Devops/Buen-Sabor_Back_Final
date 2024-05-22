package NetDevops.BuenSabor.contoller;

import NetDevops.BuenSabor.entities.UnidadMedida;
import NetDevops.BuenSabor.service.impl.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// GET http://localhost:8080/api/unidad-medida/

@RestController
@RequestMapping("/api/unidad-medida")
public class UnidadMedidaController {
    @Autowired
    private UnidadMedidaService unidadMedidaService;

    //region CRUD Basico
    @GetMapping("/")
    public ResponseEntity<?> mostrarLista(){
        try {
            return ResponseEntity.ok(unidadMedidaService.mostrarLista());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try {
            return ResponseEntity.ok(unidadMedidaService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> cargar(@RequestBody UnidadMedida unidadMedida){
        try {
            return ResponseEntity.ok(unidadMedidaService.cargar(unidadMedida));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,@RequestBody UnidadMedida unidadMedida){
        try {
            return ResponseEntity.ok(unidadMedidaService.actualizar(id, unidadMedida));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(unidadMedidaService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //endregion

    @PostMapping("/reactivate/{id}")
    public ResponseEntity<?> reactivate(@PathVariable Long id) {
        try {
            unidadMedidaService.reactivate(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/traerTodo/")
    public ResponseEntity<?> traerTodo() {
        try {
            return ResponseEntity.ok(unidadMedidaService.traerTodo());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
