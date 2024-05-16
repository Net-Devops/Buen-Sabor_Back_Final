package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.entities.ArticuloInsumo;
import NetDevops.BuenSabor.entities.ImagenArticulo;
import NetDevops.BuenSabor.repository.IAriticuloInsumoRepository;
import NetDevops.BuenSabor.service.IArticuloInsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloInsumoService implements IArticuloInsumoService {

    @Autowired
    private IAriticuloInsumoRepository articuloInsumoRepository;

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

            for (ImagenArticulo imagen : articuloInsumo.getImagenes()) {
                imagen.setArticulo(articuloInsumo);
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
            return articuloInsumoRepository.save(articuloInsumo);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    //endregion
}
