package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.entities.Promocion;
import NetDevops.BuenSabor.repository.IPromocionRepository;
import NetDevops.BuenSabor.service.IPromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Promocion update(Long id, Promocion promocion) throws Exception {
        try {
            //promocion.setId(id);
            return promocionRepository.save(promocion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
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
