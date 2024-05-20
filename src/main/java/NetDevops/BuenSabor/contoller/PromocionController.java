package NetDevops.BuenSabor.contoller;

import NetDevops.BuenSabor.entities.Promocion;
import NetDevops.BuenSabor.service.IPromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promociones")
public class PromocionController {
    @Autowired
    private IPromocionService promocionService;

    @GetMapping("")
    public ResponseEntity getAll() {
            try {
                return ResponseEntity.ok().body(promocionService.getAll());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
@GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(promocionService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("")
    public ResponseEntity save(@RequestBody Promocion promocion) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(promocionService.save(promocion));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Promocion promocion) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(promocionService.update(id, promocion));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(promocionService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

