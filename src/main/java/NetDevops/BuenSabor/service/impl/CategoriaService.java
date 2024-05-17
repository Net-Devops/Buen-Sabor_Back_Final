package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.entities.Articulo;
import NetDevops.BuenSabor.entities.Categoria;
import NetDevops.BuenSabor.repository.ICategoriaRepository;
import NetDevops.BuenSabor.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoriaService implements ICategoriaService {
    @Autowired
    private ICategoriaRepository categoriaRepository;

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
    public Categoria actualizar(Long id, Categoria categoria) throws Exception {
        try {
            if (!categoriaRepository.existsByIdAndEliminadoFalse(id)) {
                throw new Exception("No existe una categoria con ese id");
            }
            if (categoriaRepository.existsByDenominacionAndEliminadoFalse(categoria.getDenominacion())) {
                throw new Exception("Ya existe una categoria con esa denominacion");
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




    public Categoria agregarArticulo(Long id, Articulo articulo) throws Exception {
        try {
            if (!categoriaRepository.existsByIdAndEliminadoFalse(id)) {
                throw new Exception("No existe una categoria con ese id");
            }
            Categoria categoria = categoriaRepository.findByIdAndEliminadoFalse(id);
            categoria.getArticulos().add(articulo);
            return categoriaRepository.save(categoria);
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
