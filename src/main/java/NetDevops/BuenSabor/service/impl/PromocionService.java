package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.entities.ImagenPromocion;
import NetDevops.BuenSabor.entities.Promocion;
import NetDevops.BuenSabor.entities.PromocionDetalle;
import NetDevops.BuenSabor.repository.IPromocionDetalleRepository;
import NetDevops.BuenSabor.repository.IPromocionRepository;
import NetDevops.BuenSabor.service.IPromocionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PromocionService implements IPromocionService {
    @Autowired
    private IPromocionRepository promocionRepository;
    @Autowired
    private IPromocionDetalleRepository promocionDetalleRepository;

    @Override
public Promocion save(Promocion promocion) throws Exception {
    try {
        // Establecer la relación bidireccional con PromocionDetalle
        for (PromocionDetalle detalle : promocion.getPromocionDetalles()) {
            detalle.setPromocion(promocion);
        }

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


@Override
@Transactional
public Promocion update(Long id, Promocion newPromocion) {
    try {
        if (promocionRepository.findById(id).isPresent()) {
            Promocion existingPromocion = promocionRepository.findById(id).get();

            if (newPromocion.getDenominacion() != null) {
                existingPromocion.setDenominacion(newPromocion.getDenominacion());
            }
            if (newPromocion.getFechaDesde() != null) {
                existingPromocion.setFechaDesde(newPromocion.getFechaDesde());
            }
            if (newPromocion.getFechaHasta() != null) {
                existingPromocion.setFechaHasta(newPromocion.getFechaHasta());
            }
            if (newPromocion.getHoraDesde() != null) {
                existingPromocion.setHoraDesde(newPromocion.getHoraDesde());
            }
            if (newPromocion.getHoraHasta() != null) {
                existingPromocion.setHoraHasta(newPromocion.getHoraHasta());
            }
            if (newPromocion.getDescripcionDescuento() != null) {
                existingPromocion.setDescripcionDescuento(newPromocion.getDescripcionDescuento());
            }
            if (newPromocion.getPrecioPromocional() != null) {
                existingPromocion.setPrecioPromocional(newPromocion.getPrecioPromocional());
            }
            if (newPromocion.getTipoPromocion() != null) {
                existingPromocion.setTipoPromocion(newPromocion.getTipoPromocion());
            }

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

            existingPromocion.setImagenes(newPromocion.getImagenes());

            return promocionRepository.save(existingPromocion);

        } else {
            throw new Exception("No existe la promoción con el id proporcionado");
        }
    } catch (Exception e) {
        throw new RuntimeException("Error al actualizar la promoción", e);
    }
}


@Override
public boolean delete(Long id) throws Exception {
    try {
        if (promocionRepository.existsById(id)) {
            Promocion promocion = promocionRepository.findById(id).get();
            boolean nuevoEstado = !promocion.isEliminado();
            promocion.setEliminado(nuevoEstado);

            // Cambiar el estado de eliminado de las ImagenPromocion asociadas
            for (ImagenPromocion imagen : promocion.getImagenes()) {
                imagen.setEliminado(nuevoEstado);
            }

            // Cambiar el estado de eliminado de las PromocionDetalle asociadas
            for (PromocionDetalle detalle : promocion.getPromocionDetalles()) {
                detalle.setEliminado(nuevoEstado);
            }

            promocionRepository.save(promocion);
            return true;
        } else {
            throw new Exception();
        }
    } catch (Exception e) {
        throw new Exception(e.getMessage());
    }
}

    @Override
    public Set<Promocion> getAllNotDeleted() throws Exception {
        try {
            return promocionRepository.findByEliminadoFalse().stream().collect(Collectors.toSet());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean reactivate(Long id) throws Exception {
        try {
            if (promocionRepository.existsById(id)) {
                Promocion promocion = promocionRepository.findById(id).get();
                if (promocion.isEliminado()) {
                    promocion.setEliminado(false);

                    // Reactivar las ImagenPromocion asociadas
                    for (ImagenPromocion imagen : promocion.getImagenes()) {
                        if (imagen.isEliminado()) {
                            imagen.setEliminado(false);
                        }
                    }

                    // Reactivar las PromocionDetalle asociadas
                    for (PromocionDetalle detalle : promocion.getPromocionDetalles()) {
                        if (detalle.isEliminado()) {
                            detalle.setEliminado(false);
                        }
                    }

                    promocionRepository.save(promocion);
                    return true;
                } else {
                    throw new Exception("La promoción con el id proporcionado no está eliminada");
                }
            } else {
                throw new Exception("No existe la promoción con el id proporcionado");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

public void deleteAllPromocionDetalles(Long promocionId) throws Exception {
    try {
        List<PromocionDetalle> detalles = promocionDetalleRepository.findByPromocionId(promocionId);
        for (PromocionDetalle detalle : detalles) {
            Promocion promocion = detalle.getPromocion();
            detalle.setPromocion(null);
            promocion.getPromocionDetalles().remove(detalle); // Elimina la referencia en promocion_promocion_detalles
            promocionDetalleRepository.save(detalle);
            promocionDetalleRepository.delete(detalle);
        }
    } catch (Exception e) {
        throw new Exception(e.getMessage());
    }
}


}
