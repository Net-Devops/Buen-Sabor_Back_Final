package NetDevops.BuenSabor.contoller;

import NetDevops.BuenSabor.entities.Articulo;
import NetDevops.BuenSabor.entities.Categoria;
import NetDevops.BuenSabor.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// GET http://localhost:8080/api/categorias/

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
    public ResponseEntity<?> actualizarCategoriaPadre(@PathVariable Long id, @RequestBody Categoria categoria) {
        try {
            return ResponseEntity.ok().body(categoriaService.actualizarCategoriaPadre(id, categoria));
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
    @PostMapping("/agregar/subcategoria/{id}")
    public ResponseEntity<?> crearSubCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        try {
            return ResponseEntity.ok().body(categoriaService.agregarSubCategoria(id, categoria));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //agrega un articulo a una categoria
//    @PutMapping("/agregar/articulo/")
//    public ResponseEntity<?> agregarArticulo(@PathVariable Long id, @RequestBody Articulo articulo) {
//        try {
//            return ResponseEntity.ok().body(categoriaService.agregarArticulo(id, articulo));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

   @PostMapping("/agregar/articulo")
public ResponseEntity<?> agregarArticulo(@RequestParam("idCategoria") Long idCategoria, @RequestParam("idArticulo") Long idArticulo) {
    try {
        return ResponseEntity.ok().body(categoriaService.agregarArticulo(idCategoria, idArticulo));
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

    @DeleteMapping("/eliminar/articulo/{idSubCategoria}/{idArticulo}")
    public ResponseEntity<?> eliminarArticuloDeSubCategoria(@PathVariable Long idSubCategoria, @PathVariable Long idArticulo) {
        try {
            return ResponseEntity.ok().body(categoriaService.eliminarArticuloDeSubCategoria(idSubCategoria, idArticulo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/actualizar/subcategoria/{idSubCategoria}")
    public ResponseEntity<?> actualizarSubCategoria(@PathVariable Long idSubCategoria, @RequestBody Categoria nuevaSubCategoria) {
        try {
            return ResponseEntity.ok().body(categoriaService.actualizarSubCategoria(idSubCategoria, nuevaSubCategoria));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
