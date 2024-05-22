package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.entities.Empresa;
import NetDevops.BuenSabor.entities.Sucursal;
import NetDevops.BuenSabor.repository.IEmpresaRepository;
import NetDevops.BuenSabor.repository.ISucursalRepository;
import NetDevops.BuenSabor.service.IEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService implements IEmpresaService {
    @Autowired
    private IEmpresaRepository empresaRepository;
    @Autowired
    private ISucursalRepository sucursalRepository;

    @Override
    public Empresa save(Empresa empresa) throws Exception {
        try {
            return empresaRepository.save(empresa);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Empresa update(Long id, Empresa empresa) throws Exception {
        try {
            empresa.setId(id);
            return empresaRepository.save(empresa);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
public boolean delete(Long id) throws Exception {
    try {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(() -> new Exception("No se encontró la empresa con el id proporcionado"));

        // Verificar si la empresa tiene alguna sucursal asociada activa
        List<Sucursal> sucursalesActivas = sucursalRepository.findByEmpresaIdAndEliminadoFalse(id);
        if (!sucursalesActivas.isEmpty()) {
            throw new Exception("No se puede eliminar la empresa porque tiene una sucursal asociada activa");
        }

        empresa.setEliminado(true);
        empresaRepository.save(empresa);
        return true;
    } catch (Exception e) {
        throw new Exception(e.getMessage());
    }
}

    @Override
    public boolean reactivate(Long id) throws Exception {
        try {
            if (empresaRepository.existsById(id)) {
                Empresa empresa = empresaRepository.findById(id).orElseThrow(() -> new Exception("No se encontró la empresa con el id proporcionado"));
                if (empresa.isEliminado()) {
                    empresa.setEliminado(false);
                    empresaRepository.save(empresa);
                    return true;
                } else {
                    throw new Exception("La Empresa con el id proporcionado no está eliminada");
                }
            } else {
                throw new Exception("No existe la Empresa con el id proporcionado");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Empresa> traerTodo() throws Exception {
        try {
            return empresaRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Empresa traerPorId(Long id) throws Exception {
        try {
            return empresaRepository.findById(id).get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
