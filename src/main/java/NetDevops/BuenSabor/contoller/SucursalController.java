package NetDevops.BuenSabor.contoller;

import NetDevops.BuenSabor.dto.sucursal.SucursalDto;
import NetDevops.BuenSabor.entities.Sucursal;
import NetDevops.BuenSabor.service.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// GET http://localhost:8080/api/sucursal/

@RestController
@RequestMapping("/api/sucursal")
public class SucursalController {
    @Autowired
    private ISucursalService sucursalService;

    //region CRUD Basico
    @GetMapping("/traer-todo/")
    public ResponseEntity<?> mostrarLista(){
        try {
            return ResponseEntity.ok(sucursalService.traerTodo());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try {
            return ResponseEntity.ok(sucursalService.traerPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/reactivate/{id}")
    public ResponseEntity<?> reactivar(@PathVariable Long id){
        try {
            return ResponseEntity.ok(sucursalService.reactivate(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(sucursalService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> cargar(@RequestBody Sucursal sucursal){
        try {
            return ResponseEntity.ok(sucursalService.save(sucursal));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,@RequestBody Sucursal sucursal){
        try {
            return ResponseEntity.ok(sucursalService.update(id, sucursal));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/lista-sucursal/{empresaId}")
    public ResponseEntity<?> traerPorEmpresaId(@PathVariable Long empresaId){
        try {
            return ResponseEntity.ok(sucursalService.traerPorEmpresaId(empresaId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/guardar-sucursal-dto/")
    public ResponseEntity<?> guardarSucursalDto(@RequestBody SucursalDto sucursalDto){
        try {
            return ResponseEntity.ok(sucursalService.guardarSucursalDto(sucursalDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
