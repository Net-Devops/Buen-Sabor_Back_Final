package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.dto.ArticuloManufacturadoTablaDto;
import NetDevops.BuenSabor.entities.ArticuloManufacturado;
import NetDevops.BuenSabor.entities.ArticuloManufacturadoDetalle;
import NetDevops.BuenSabor.entities.ImagenArticulo;
import NetDevops.BuenSabor.repository.IArticuloManufacturadoDetalleRepository;
import NetDevops.BuenSabor.repository.IArticuloManufacturadoRepository;
import NetDevops.BuenSabor.repository.ImagenArticuloRepository;
import NetDevops.BuenSabor.service.IArticuloManufacturadoService;
import NetDevops.BuenSabor.service.funcionalidades.Funcionalidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class ArticuloManufacturadoService implements IArticuloManufacturadoService {

    @Autowired
    private IArticuloManufacturadoRepository articuloManufacturadoRepository;
    @Autowired
    private IArticuloManufacturadoDetalleRepository detalleRepository;
    @Autowired
    private ImagenArticuloRepository imagenRepository;
    @Autowired
    private Funcionalidades funcionalidades;

//region Crud Basico

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

        // Guardar las imágenes en base64 que vienen con el ArticuloManufacturado
        if (articuloManufacturado.getImagenes() != null) {
            for (ImagenArticulo imagen : articuloManufacturado.getImagenes()) {
                try {
                    String rutaImagen = funcionalidades.guardarImagen(imagen.getUrl(), "nombreArchivo"); // Reemplaza "nombreArchivo" con el nombre de archivo que desees
                    imagen.setUrl(rutaImagen); // Actualizar el campo url en ImagenArticulo con la ruta de la imagen
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        for (ArticuloManufacturadoDetalle detalle : articuloManufacturado.getArticuloManufacturadoDetalles()) {
            detalle.setArticuloManufacturado(articuloManufacturado);
        }

        return articuloManufacturadoRepository.save(articuloManufacturado);
    } catch (Exception e) {
        throw new Exception(e);
    }
}

    //endregion

    //region Buscar por ID
    @Override
public ArticuloManufacturado buscarPorId(Long id) throws Exception {
    try {
        ArticuloManufacturado Manufacturado = articuloManufacturadoRepository.findByIdAndEliminadoFalse(id);
        if (Manufacturado == null) {
            throw new Exception("No se encontro el articulo");
        }

        // Convertir las imágenes a base64
        if (Manufacturado.getImagenes() != null) {
            for (ImagenArticulo imagen : Manufacturado.getImagenes()) {
                try {
                    String imagenBase64 = funcionalidades.convertirImagenABase64(imagen.getUrl());
                    imagen.setUrl(imagenBase64); // Actualizar el campo url en ImagenArticulo con la imagen en base64
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
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
        Set<ArticuloManufacturado> articulosManufacturados = articuloManufacturadoRepository.findByEliminadoFalse();

        // Convertir las imágenes a base64
        for (ArticuloManufacturado articuloManufacturado : articulosManufacturados) {
            if (articuloManufacturado.getImagenes() != null) {
                for (ImagenArticulo imagen : articuloManufacturado.getImagenes()) {
                    try {
                        String imagenBase64 = funcionalidades.convertirImagenABase64(imagen.getUrl());
                        imagen.setUrl(imagenBase64); // Actualizar el campo url en ImagenArticulo con la imagen en base64
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return articulosManufacturados;
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

            // Eliminar de manera lógica ArticuloManufacturadoDetalle
            for (ArticuloManufacturadoDetalle detalle : articuloManufacturado.getArticuloManufacturadoDetalles()) {
                detalle.setEliminado(true);
                detalleRepository.save(detalle); // Asegúrate de tener un repositorio para ArticuloManufacturadoDetalle
            }

            // Eliminar de manera lógica las imágenes
            for (ImagenArticulo imagen : articuloManufacturado.getImagenes()) {
                imagen.setEliminado(true);
                imagenRepository.save(imagen); // Asegúrate de tener un repositorio para ImagenArticulo
            }

            articuloManufacturadoRepository.save(articuloManufacturado);
            return true;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    //endregion

    //region Actualizar

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

        //region Logica para eliminar Detalles
        Set<ArticuloManufacturadoDetalle> detallesViejos = detalleRepository.findByArticuloManufacturado_Id(id);
        Set<ArticuloManufacturadoDetalle> detallesNuevos = articuloManufacturado.getArticuloManufacturadoDetalles();

        detallesViejos.forEach(detalleViejo -> {
            if (!detallesNuevos.contains(detalleViejo)) {
                detalleRepository.delete(detalleViejo);
            }
        });
        //endregion

        //region Logica para eliminar Imagenes
        Set<ImagenArticulo> imagenesViejas = imagenRepository.findByArticulo_Id(id);
        Set<ImagenArticulo> imagenesNuevas = articuloManufacturado.getImagenes();

        imagenesViejas.forEach(imagenVieja -> {
            if (!imagenesNuevas.contains(imagenVieja)) {
                imagenVieja.setEliminado(true);
                imagenVieja.setArticulo(null);
                imagenRepository.save(imagenVieja);
            } else {
                // Convertir la imagen a base64
                try {
                    String imagenBase64 = funcionalidades.convertirImagenABase64(imagenVieja.getUrl());
                    imagenVieja.setUrl(imagenBase64); // Actualizar el campo url en ImagenArticulo con la imagen en base64
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //endregion

        return articuloManufacturadoRepository.save(articuloManufacturado);
    }catch (Exception e){
        throw new Exception(e);
    }
}
    //endregion

    //endregion

    //region Dtos

   public Set<ArticuloManufacturadoTablaDto> tablaArticuloManufacturado() throws Exception {
    try {
        Set<ArticuloManufacturado> articulos = articuloManufacturadoRepository.findByEliminadoFalse();
        Set<ArticuloManufacturadoTablaDto> articulosDto = new HashSet<>();

        for (ArticuloManufacturado articulo : articulos) {
            ArticuloManufacturadoTablaDto dto = new ArticuloManufacturadoTablaDto();
            dto.setId(articulo.getId());
            dto.setCodigo(articulo.getCodigo());
            dto.setDenominacion(articulo.getDenominacion());

            // Convertir la imagen a base64
            if (articulo.getImagenes() != null && !articulo.getImagenes().isEmpty()) {
                ImagenArticulo imagen = articulo.getImagenes().iterator().next();
                try {
                    String imagenBase64 = funcionalidades.convertirImagenABase64(imagen.getUrl());
                    dto.setImagen(imagenBase64); // Actualizar el campo imagen en ArticuloManufacturadoTablaDto con la imagen en base64
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            dto.setPrecioVenta(articulo.getPrecioVenta());
            dto.setDescripcion(articulo.getDescripcion());
            dto.setTiempoEstimadoCocina(articulo.getTiempoEstimadoMinutos());

            articulosDto.add(dto);
        }

        return articulosDto;

    }catch (Exception e){
        throw new Exception(e);
    }
}


    //endregion
}
