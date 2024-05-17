package NetDevops.BuenSabor.contoller;

import NetDevops.BuenSabor.entities.Articulo;
import NetDevops.BuenSabor.entities.Categoria;
import NetDevops.BuenSabor.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaControllador {
    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/")
    public ResponseEntity<?> lista() {
        try {
            return ResponseEntity.ok().body(categoriaService.lista());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(categoriaService.buscar(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> cargar(@RequestBody Categoria categoria) {
        try {
            return ResponseEntity.ok().body(categoriaService.cargar(categoria));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        try {
            return ResponseEntity.ok().body(categoriaService.actualizar(id, categoria));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(categoriaService.eliminar(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //agrega una subcategoria a una categoria
    @PostMapping("/crear/subcategoria/")
    public ResponseEntity<?> crearSubCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        try {
            return ResponseEntity.ok().body(categoriaService.agregarSubCategoria(id, categoria));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //agrega un articulo a una categoria
    @PostMapping("/crear/articulo/")
    public ResponseEntity<?> crearArticulo(@PathVariable Long id, @RequestBody Articulo articulo) {
        try {
            return ResponseEntity.ok().body(categoriaService.agregarArticulo(id, articulo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //obtiene todas las subcategorias
    @GetMapping("/subcategorias/")
    public ResponseEntity<?> obtenerSubCategorias() {
        try {
            return ResponseEntity.ok().body(categoriaService.obtenerSubCategorias());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //obtiene todas las categorias con subcategorias
    @GetMapping("/categoriasConSubcategorias/")
    public ResponseEntity<?> obtenerCategoriasConSubCategorias() {
        try {
            return ResponseEntity.ok().body(categoriaService.obtenerCategoriasConSubCategorias());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
