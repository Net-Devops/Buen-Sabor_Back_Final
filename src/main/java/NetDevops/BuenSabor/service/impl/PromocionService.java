package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.entities.ImagenPromocion;
import NetDevops.BuenSabor.entities.Promocion;
import NetDevops.BuenSabor.entities.PromocionDetalle;
import NetDevops.BuenSabor.repository.IPromocionRepository;
import NetDevops.BuenSabor.service.IPromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PromocionService implements IPromocionService {
    @Autowired
    private IPromocionRepository promocionRepository;

    @Override
    public Promocion save(Promocion promocion) throws Exception {
        try {
            return promocionRepository.save(promocion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Set<Promocion> getAll() throws Exception {
        try {
            return promocionRepository.findAll().stream().collect(Collectors.toSet());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Promocion getById(Long id) throws Exception {
       try {
              return promocionRepository.findById(id).get();
         } catch (Exception e) {
              throw new Exception(e.getMessage());
       }
    }
//
//    @Override
//    public Promocion update(Long id, Promocion promocion) throws Exception {
//        try {
//            //promocion.setId(id);
//            return promocionRepository.save(promocion);
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }

@Override
public Promocion update(Long id, Promocion newPromocion) {
    try {
        Promocion updatedPromocion;

        if (promocionRepository.findById(id).isPresent()) {
            Promocion existingPromocion = promocionRepository.findById(id).get();

            existingPromocion.setDenominacion(newPromocion.getDenominacion());
            existingPromocion.setFechaDesde(newPromocion.getFechaDesde());
            existingPromocion.setFechaHasta(newPromocion.getFechaHasta());
            existingPromocion.setHoraDesde(newPromocion.getHoraDesde());
            existingPromocion.setHoraHasta(newPromocion.getHoraHasta());
            existingPromocion.setDescripcionDescuento(newPromocion.getDescripcionDescuento());
            existingPromocion.setPrecioPromocional(newPromocion.getPrecioPromocional());
            existingPromocion.setTipoPromocion(newPromocion.getTipoPromocion());

            // Marcar como eliminadas las ImagenPromocion que no están en newPromocion
            for (ImagenPromocion imagen : existingPromocion.getImagenes()) {
                if (!newPromocion.getImagenes().contains(imagen)) {
                    imagen.setEliminado(true);
                }
            }

            // Marcar como eliminadas las PromocionDetalle que no están en newPromocion
            for (PromocionDetalle detalle : existingPromocion.getPromocionDetalles()) {
                if (!newPromocion.getPromocionDetalles().contains(detalle)) {
                    detalle.setEliminado(true);
                }
            }

            existingPromocion.setPromocionDetalles(newPromocion.getPromocionDetalles());
            existingPromocion.setImagenes(newPromocion.getImagenes());

            return promocionRepository.save(existingPromocion);

        } else {
            throw new Exception("No existe la promoción con el id proporcionado");
        }
    } catch (Exception e) {
        // Aquí puedes manejar la excepción como prefieras
        throw new RuntimeException("Error al actualizar la promoción", e);
    }
}




    @Override
    public boolean delete(Long id) throws Exception {
        try {
            if (promocionRepository.existsById(id)) {
                Promocion promocion = promocionRepository.findById(id).get();
                promocion.setEliminado(true);
                promocionRepository.save(promocion);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
