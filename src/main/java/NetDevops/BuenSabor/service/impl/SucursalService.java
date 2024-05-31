package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.entities.Sucursal;
import NetDevops.BuenSabor.repository.ISucursalRepository;
import NetDevops.BuenSabor.service.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements ISucursalService {

    @Autowired
    private ISucursalRepository sucursalRepository;

  @Override
public Sucursal save(Sucursal sucursal) throws Exception {
    try {

        if (sucursalRepository.findByNombre(sucursal.getNombre())){
            throw new Exception("Ya existe una sucursal con el nombre proporcionado");
        }
        return sucursalRepository.save(sucursal);
    } catch (Exception e) {
        throw new Exception(e.getMessage());
    }
}

    @Override
    public boolean delete(Long id) throws Exception {
        try {
                Sucursal sucursal = sucursalRepository.findById(id).orElseThrow(() -> new Exception("No se encontró la sucursal con el id proporcionado"));
                sucursal.setEliminado(true);
                sucursalRepository.save(sucursal);
                return true;
        } catch (Exception e) {
        throw new Exception(e.getMessage());
        }
    }

    @Override
    public Sucursal update(Long id, Sucursal sucursal) throws Exception {
        try {
            if(sucursalRepository.existsByNombreAndNotId(sucursal.getNombre(), id)){
                throw new Exception("Ya existe una sucursal con el nombre proporcionado");
            }
            sucursal.setId(id);
            return sucursalRepository.save(sucursal);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Sucursal> traerTodo() throws Exception {
        try {
            return sucursalRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Sucursal traerPorId(Long id) throws Exception {
        try {
            return sucursalRepository.findById(id).orElseThrow(() -> new Exception("No se encontró la sucursal con el id proporcionado"));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Sucursal reactivate(Long id) throws Exception {
        try {
            Sucursal sucursal = sucursalRepository.findById(id).orElseThrow(() -> new Exception("No se encontró la sucursal con el id proporcionado"));
            sucursal.setEliminado(false);
            return sucursalRepository.save(sucursal);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Sucursal> traerPorEmpresaId(Long empresaId) throws Exception {
        try {
            return sucursalRepository.findByEmpresaId(empresaId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
