package NetDevops.BuenSabor.contoller;

import NetDevops.BuenSabor.entities.ArticuloManufacturado;
import NetDevops.BuenSabor.entities.Views;
import NetDevops.BuenSabor.service.IArticuloManufacturadoService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articulos/manufacturados")
public class ArticuloManufacturadoController {
    @Autowired
    private IArticuloManufacturadoService articuloManufacturadoService;


    //region CRUD Basico
    @JsonView(Views.Public.class)
    @GetMapping("/")
    public ResponseEntity<?> Lista() {
        try {
            return ResponseEntity.status(200).body(articuloManufacturadoService.listaArticuloManufacturado());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(200).body(articuloManufacturadoService.buscarPorId(id));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //@JsonView(Views.Public.class)
    @PostMapping("/")
    public ResponseEntity<?> cargarArticuloManufacturado(@RequestBody ArticuloManufacturado articuloManufacturado) {
        try {
            return ResponseEntity.ok().body(articuloManufacturadoService.cargarArticuloManufacturado(articuloManufacturado));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarArticuloManufacturado(@PathVariable Long id, @RequestBody ArticuloManufacturado articuloManufacturado) {
        try {
            return ResponseEntity.status(200).body(articuloManufacturadoService.actualizarArticuloManufacturado(id, articuloManufacturado));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarArticuloManufacturado(@PathVariable Long id) {
        try {
            return ResponseEntity.status(200).body(articuloManufacturadoService.eliminarArticuloManufacturado(id));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //endregion

    //region Dtos

    @GetMapping("/tabla/")
    public ResponseEntity<?> tabla() {
        try {

            return ResponseEntity.ok().body(articuloManufacturadoService.tablaArticuloManufacturado());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    //endregion



}
