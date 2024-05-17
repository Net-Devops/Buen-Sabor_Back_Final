package NetDevops.BuenSabor.service;

import NetDevops.BuenSabor.entities.Articulo;
import NetDevops.BuenSabor.entities.Categoria;

import java.util.Set;

public interface ICategoriaService {


    public Categoria cargar(Categoria categoria) throws Exception;
    public Categoria actualizar(Long id, Categoria categoria) throws Exception;
    public Set<Categoria> lista()throws Exception;
    public Categoria buscar(Long id)throws Exception;
    public boolean eliminar(Long id)throws Exception;
    public Set<Categoria> obtenerSubCategorias() throws Exception;
    public Set<Categoria> obtenerCategoriasConSubCategorias() throws Exception;
    public Categoria agregarSubCategoria(Long id, Categoria subCategoria) throws Exception;
    public Categoria agregarArticulo(Long id, Articulo articulo) throws Exception;


}
