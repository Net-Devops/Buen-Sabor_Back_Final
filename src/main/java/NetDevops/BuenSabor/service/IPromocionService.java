package NetDevops.BuenSabor.service;

import NetDevops.BuenSabor.entities.Promocion;

import java.util.Set;

public interface IPromocionService {

    public Promocion save(Promocion promocion) throws Exception;
    public Set<Promocion> getAll() throws Exception;
    public Promocion getById(Long id) throws Exception;
    public Promocion update(Long id, Promocion promocion) throws Exception;
    public boolean delete(Long id) throws Exception;


}
