package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.entities.ArticuloInsumo;
import NetDevops.BuenSabor.entities.ImagenArticulo;
import NetDevops.BuenSabor.repository.IAriticuloInsumoRepository;
import NetDevops.BuenSabor.repository.ImagenArticuloRepository;
import NetDevops.BuenSabor.service.IArticuloInsumoService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ArticuloInsumoService implements IArticuloInsumoService {
    private final Path root = Paths.get("images");

    @Autowired
    private IAriticuloInsumoRepository articuloInsumoRepository;
    @Autowired
    private ImagenArticuloRepository imagenRepository;

    //region Eliminacion
    @Override
    public boolean deleteById(Long id) throws Exception {

        try {
            ArticuloInsumo articuloInsumo = articuloInsumoRepository.findById(id).orElseThrow(() -> new Exception("No se encontro el articulo"));
            articuloInsumo.setEliminado(true);
            articuloInsumoRepository.save(articuloInsumo);
            return true;

        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    //endregion

    //region Mostrar Lista
    @Override
    public List<ArticuloInsumo> mostrarLista() throws Exception {
        try {
            return articuloInsumoRepository.findByEliminadoFalse();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    //endregion

    //region Cargar
    @Override
    public ArticuloInsumo cargar(ArticuloInsumo articuloInsumo) throws Exception {
        try {

            if(articuloInsumoRepository.existsByCodigoAndEliminadoFalse(articuloInsumo.getCodigo())){
                throw new Exception("Ya existe un articulo con ese codigo");
            }
            if (articuloInsumoRepository.existsByDenominacionAndEliminadoFalse(articuloInsumo.getDenominacion())){
                throw new Exception("Ya existe un articulo con esa denominacion");
            }

//            for (ImagenArticulo imagen : articuloInsumo.getImagenes()) {
//                imagen.setArticulo(articuloInsumo);
//            }
//            articuloInsumoRepository.save(articuloInsumo);
//            return articuloInsumoRepository.findById(articuloInsumo.getId()).get();

            if (articuloInsumo.getImagenes() != null) {
                for (ImagenArticulo imagen : articuloInsumo.getImagenes()) {
                    // Decodificar la imagen base64 en un array de bytes
                    byte[] bytes = Base64.decodeBase64(imagen.getUrl());

                    // Crear un InputStream a partir del array de bytes
                    InputStream inputStream = new ByteArrayInputStream(bytes);

                    // Generar un nombre de archivo único para cada imagen
                    String filename = UUID.randomUUID().toString() + ".jpg";

                    // Usar Files.copy() para guardar la imagen en el directorio deseado
                    Path path = this.root.resolve(filename);
                    Files.copy(inputStream, path);

                    // Actualizar el campo url en ImagenArticulo
                    imagen.setUrl(path.toString());
                    imagen.setArticulo(articuloInsumo);
                }
            }

            articuloInsumoRepository.save(articuloInsumo);
            return articuloInsumoRepository.findById(articuloInsumo.getId()).get();




        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception(e);
        }
    }

    //endregion

    //region Buscar Por Id
    @Override
    public ArticuloInsumo buscarPorId(Long id) throws Exception {
        try {
            ArticuloInsumo articuloInsumo = articuloInsumoRepository.findByIdAndEliminadoFalse(id);
            if (articuloInsumo == null) {
                throw new Exception("No se encontro el articulo");
            }
            return articuloInsumo;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    //endregion

    //region Actualizar
    @Override
    public ArticuloInsumo actualizar(Long id, ArticuloInsumo articuloInsumo) throws Exception {
        try {
            if (!articuloInsumoRepository.existsById(id)){
                throw new Exception("No se encontro el articulo");
            }

            if (articuloInsumoRepository.existsByCodigoAndEliminadoFalseAndIdNot(articuloInsumo.getCodigo(), id)){
                throw new Exception("Ya existe un articulo con ese codigo");
            }
            if (articuloInsumoRepository.existsByDenominacionAndEliminadoFalseAndIdNot(articuloInsumo.getDenominacion(), id)){
                throw new Exception("Ya existe un articulo con esa denominacion");
            }

            //region Logica para eliminar Imagenes
            Set<ImagenArticulo> imagenesViejas = imagenRepository.findByArticulo_Id(id);
            Set<ImagenArticulo> imagenesNuevas = articuloInsumo.getImagenes();

            imagenesViejas.forEach(imagenVieja -> {
                if (!imagenesNuevas.contains(imagenVieja)) {
                    imagenVieja.setEliminado(true);
                    imagenVieja.setArticulo(null);
                    imagenRepository.save(imagenVieja);
                }
            });
            //endregion



            return articuloInsumoRepository.save(articuloInsumo);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    //endregion
}
