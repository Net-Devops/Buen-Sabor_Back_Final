package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.entities.Articulo;
import NetDevops.BuenSabor.entities.Categoria;
import NetDevops.BuenSabor.repository.IArticuloRepository;
import NetDevops.BuenSabor.repository.ICategoriaRepository;
import NetDevops.BuenSabor.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoriaService implements ICategoriaService {
    @Autowired
    private ICategoriaRepository categoriaRepository;
    @Autowired
    private IArticuloRepository articuloRepository;

    @Override
    public Categoria cargar(Categoria categoria) throws Exception {
        try {
            if (categoriaRepository.existsByDenominacionAndEliminadoFalse(categoria.getDenominacion())) {
                throw new Exception("Ya existe una categoria con esa denominacion");
            }

            return categoriaRepository.save(categoria);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

@Override
public Categoria actualizarCategoriaPadre(Long id, Categoria nuevaCategoria) throws Exception {
    try {
        if (!categoriaRepository.existsByIdAndEliminadoFalse(id)) {
            throw new Exception("No existe una categoria con ese id");
        }
        if (categoriaRepository.existsByDenominacionAndEliminadoFalse(nuevaCategoria.getDenominacion())) {
            throw new Exception("Ya existe una categoria con esa denominacion");
        }
        Categoria categoria = categoriaRepository.findByIdAndEliminadoFalse(id);

        // Actualizar la denominación
        categoria.setDenominacion(nuevaCategoria.getDenominacion());

        // Manejar las subcategorías
        for (Categoria subCategoria : new HashSet<>(categoria.getSubCategorias())) {
            if (!nuevaCategoria.getSubCategorias().contains(subCategoria)) {
                if (!subCategoria.getArticulos().isEmpty()) {
                    throw new Exception("Hay subcategorias que tienen articulos");
                }
                categoria.getSubCategorias().remove(subCategoria);
            }
        }
        for (Categoria subCategoria : nuevaCategoria.getSubCategorias()) {
            if (categoriaRepository.existsByDenominacionAndEliminadoFalse(subCategoria.getDenominacion())) {
                throw new Exception("Ya existe una subcategoria con esa denominacion");
            }
            if (!categoria.getSubCategorias().contains(subCategoria)) {
                categoria.getSubCategorias().add(subCategoria);
            }
        }

        return categoriaRepository.save(categoria);
    } catch (Exception e) {
        throw new Exception(e.getMessage());
    }
}

    @Override
    public Set<Categoria> lista() throws Exception {
        try {
            if (categoriaRepository.findAll().isEmpty()) {
                throw new Exception("No hay categorias cargadas");
            }
            return categoriaRepository.findAllByEliminadoFalse();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Categoria buscar(Long id) throws Exception {
        try {
            if (!categoriaRepository.existsByIdAndEliminadoFalse(id)) {
                throw new Exception("No existe una categoria con ese id");
            }
            return categoriaRepository.findByIdAndEliminadoFalse(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean eliminar(Long id) throws Exception {
        try {
            if (!categoriaRepository.existsByIdAndEliminadoFalse(id)) {
                throw new Exception("No existe una categoria con ese id");
            }
            Categoria categoria = categoriaRepository.findByIdAndEliminadoFalse(id);
            categoria.setEliminado(true);
            categoriaRepository.save(categoria);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }




//    public Categoria agregarArticulo(Long id, Articulo articulo) throws Exception {
//        try {
//            if (!categoriaRepository.existsByIdAndEliminadoFalse(id)) {
//                throw new Exception("No existe una categoria con ese id");
//            }
//            Categoria categoria = categoriaRepository.findByIdAndEliminadoFalse(id);
//
//            categoria.getArticulos().add(articulo);
//            return categoriaRepository.save(categoria);
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }

    public Categoria agregarArticulo(Long idCategoria, Long idArticulo) throws Exception {
        try {
            if (!categoriaRepository.existsByIdAndEliminadoFalse(idCategoria)) {
                throw new Exception("No existe una categoria con ese id");
            }
            if (!articuloRepository.existsById(idArticulo)) {
                throw new Exception("No existe un articulo con ese id");
            }
            Categoria categoria = categoriaRepository.findByIdAndEliminadoFalse(idCategoria);
            Articulo articulo = articuloRepository.findById(idArticulo).orElse(null);

            categoria.getArticulos().add(articulo);
            return categoriaRepository.save(categoria);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Categoria eliminarArticuloDeSubCategoria(Long idSubCategoria, Long idArticulo) throws Exception {
        try {
            if (!categoriaRepository.existsByIdAndEliminadoFalse(idSubCategoria)) {
                throw new Exception("No existe una subcategoria con ese id");
            }
            if (!articuloRepository.existsById(idArticulo)) {
                throw new Exception("No existe un articulo con ese id");
            }
            Categoria subCategoria = categoriaRepository.findByIdAndEliminadoFalse(idSubCategoria);
            Articulo articulo = articuloRepository.findById(idArticulo).orElse(null);

            subCategoria.getArticulos().remove(articulo);
            return categoriaRepository.save(subCategoria);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Categoria actualizarSubCategoria(Long idSubCategoria, Categoria nuevaSubCategoria) throws Exception {
        try {
            if (!categoriaRepository.existsByIdAndEliminadoFalse(idSubCategoria)) {
                throw new Exception("No existe una subcategoria con ese id");
            }
            Categoria subCategoria = categoriaRepository.findByIdAndEliminadoFalse(idSubCategoria);

            // Actualizar la denominación
            subCategoria.setDenominacion(nuevaSubCategoria.getDenominacion());

            // Manejar los artículos
            for (Articulo articulo : new HashSet<>(subCategoria.getArticulos())) {
                if (!nuevaSubCategoria.getArticulos().contains(articulo)) {
                    subCategoria.getArticulos().remove(articulo);
                }
            }
            for (Articulo articulo : nuevaSubCategoria.getArticulos()) {
                if (!subCategoria.getArticulos().contains(articulo)) {
                    subCategoria.getArticulos().add(articulo);
                }
            }

            return categoriaRepository.save(subCategoria);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }



    public Categoria agregarSubCategoria(Long id, Categoria subCategoria) throws Exception {
        try {
            if (!categoriaRepository.existsByIdAndEliminadoFalse(id)) {
                throw new Exception("No existe una categoria con ese id");
            }
            Categoria categoria = categoriaRepository.findByIdAndEliminadoFalse(id);
            categoria.agregarSubCategoria(subCategoria);
            return categoriaRepository.save(categoria);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    // Obtengo las subcategorias
    public Set<Categoria> obtenerSubCategorias() throws Exception {
        try {
            if (categoriaRepository.findByCategoriaPadreIsNotNull().isEmpty()) {
                throw new Exception("No hay subcategorias");
            }
            return categoriaRepository.findByCategoriaPadreIsNotNull();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // Obtengo las categorias con subcategorias
    public Set<Categoria> obtenerCategoriasConSubCategorias() throws Exception {
        try {
            if (categoriaRepository.findAllByEliminadoFalse().isEmpty()) {
                throw new Exception("No hay categorias no eliminadas");
            }
            return categoriaRepository.findAllByEliminadoFalse();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
