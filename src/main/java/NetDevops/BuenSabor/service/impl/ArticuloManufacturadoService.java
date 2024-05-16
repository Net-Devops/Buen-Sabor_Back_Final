package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.entities.ArticuloManufacturado;
import NetDevops.BuenSabor.entities.ArticuloManufacturadoDetalle;
import NetDevops.BuenSabor.entities.ImagenArticulo;
import NetDevops.BuenSabor.repository.IArticuloManufacturadoDetalleRepository;
import NetDevops.BuenSabor.repository.IArticuloManufacturadoRepository;
import NetDevops.BuenSabor.repository.ImagenArticuloRepository;
import NetDevops.BuenSabor.service.IArticuloManufacturadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ArticuloManufacturadoService implements IArticuloManufacturadoService {

    @Autowired
    private IArticuloManufacturadoRepository articuloManufacturadoRepository;
    @Autowired
    private IArticuloManufacturadoDetalleRepository detalleRepository;
    @Autowired
    private ImagenArticuloRepository imagenRepository;



    //region Cargar
    @Override
    public ArticuloManufacturado cargarArticuloManufacturado(ArticuloManufacturado articuloManufacturado) throws Exception {
        try {

            if (articuloManufacturadoRepository.existsByDenominacionAndEliminadoFalse(articuloManufacturado.getDenominacion())) {
                throw new Exception("Ya existe un articulo con esa denominacion");
            }
            if (articuloManufacturadoRepository.existsByCodigoAndEliminadoFalse(articuloManufacturado.getCodigo())) {
                throw new Exception("Ya existe un articulo con ese codigo");
            }
            return articuloManufacturadoRepository.save(articuloManufacturado);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //endregion

    //region Buscar por ID
    @Override
    public ArticuloManufacturado buscarPorId(Long id) throws Exception {
        try {

            ArticuloManufacturado Manufacturado = articuloManufacturadoRepository.findByEliminadoFalse(id);
            if (Manufacturado == null) {
                throw new Exception("No se encontro el articulo");
            }
            return Manufacturado;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
//endregion

    //region Mostrar Lista
    @Override
    public Set<ArticuloManufacturado> listaArticuloManufacturado() throws Exception {
        try {
            return articuloManufacturadoRepository.findByEliminadoFalse();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    //endregion

    //region Eliminacion
    @Override
    public boolean eliminarArticuloManufacturado(Long id) throws Exception {
        try {
            ArticuloManufacturado articuloManufacturado = articuloManufacturadoRepository.findById(id).orElse(null);
            if (articuloManufacturado == null) {
                throw new Exception("No se encontro el articulo");
            }
            articuloManufacturado.setEliminado(true);
            articuloManufacturadoRepository.save(articuloManufacturado);
            return true;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    //endregion

    @Override
    public ArticuloManufacturado actualizarArticuloManufacturado(Long id, ArticuloManufacturado articuloManufacturado) throws Exception {
        try {

            if (!articuloManufacturadoRepository.existsById(id)) {
                throw new Exception("No se encontró el artículo");
            }
            if (articuloManufacturadoRepository.existsByCodigoAndEliminadoFalseAndIdNot(articuloManufacturado.getCodigo(), id)) {
                throw new Exception("Ya existe un articulo con ese codigo");
            }
            if (articuloManufacturadoRepository.existsByDenominacionAndEliminadoFalseAndIdNot(articuloManufacturado.getDenominacion(), id)) {
                throw new Exception("Ya existe un articulo con esa denominacion");
            }

//            Manufacturado.setDenominacion(articuloManufacturado.getDenominacion());
//            Manufacturado.setDescripcion(articuloManufacturado.getDescripcion());
//            Manufacturado.setPrecioVenta(articuloManufacturado.getPrecioVenta());
//            Manufacturado.setPreparacion(articuloManufacturado.getPreparacion());
//            Manufacturado.setCodigo(articuloManufacturado.getCodigo());


            //region Logica para eliminar Detalles
            List<ArticuloManufacturadoDetalle> detallesViejos = detalleRepository.findByArticuloManufacturado_Id(id);
            Set<ArticuloManufacturadoDetalle> detallesNuevos = articuloManufacturado.getArticuloManufacturadoDetalles();

            detallesViejos.forEach(detalleViejo -> {
                if (!detallesNuevos.contains(detalleViejo)) {
                    detalleRepository.delete(detalleViejo);
                }
            });
            //endregion

            //region Logica para eliminar Imagenes
            Set<ImagenArticulo> imagenesViejas = imagenRepository.findByArticuloManufacturado_Id(id);
            Set<ImagenArticulo> imagenesNuevas = articuloManufacturado.getImagenes();

            imagenesViejas.forEach(imagenVieja -> {
                if (!imagenesNuevas.contains(imagenVieja)) {
                    imagenVieja.setEliminado(true);
                    imagenRepository.save(imagenVieja);
                }
            });
            //endregion

            return articuloManufacturadoRepository.save(articuloManufacturado);
        }catch (Exception e){
            throw new Exception(e);
        }
    }
}
