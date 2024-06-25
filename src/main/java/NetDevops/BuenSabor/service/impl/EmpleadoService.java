package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.entities.Empleado;
import NetDevops.BuenSabor.repository.IEmpleadoRepository;
import NetDevops.BuenSabor.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService implements IEmpleadoService {
    @Autowired
    private IEmpleadoRepository empleadoRepository;

    @Override
    public Empleado crearEmpleado(Empleado empleado) throws Exception{
        try {
            return empleadoRepository.save(empleado);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Empleado actualizarEmpleado(Long id,Empleado empleado)throws Exception {
        try {
            Empleado empleadoActual = empleadoRepository.findById(id).orElse(null);
            if (empleadoActual == null) {
                return null;
            }
            empleado.setId(id);
            return empleadoRepository.save(empleado);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Empleado buscarPorId(Long idEmpleado)throws Exception {
        try {
            return empleadoRepository.findById(idEmpleado).orElse(null);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean eliminarEmpleado(Long idEmpleado) throws Exception {
        try {
            empleadoRepository.deleteById(idEmpleado);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Empleado> traerEmpleados() throws Exception{
        try {
            return empleadoRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


   /*------------------------------*/
    public List<Empleado> traerEmpleadosPorSucursal(Long sucursalId) throws Exception {
        try {
            return empleadoRepository.findBySucursalId(sucursalId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Empleado eliminarEmpleadoActualizando(Long idEmpleado) throws Exception {
        try {
            Empleado empleado = empleadoRepository.findById(idEmpleado).orElse(null);
            if (empleado == null) {
                throw new Exception("Empleado no encontrado");
            }
            // Cambiar el estado de eliminado
            empleado.setEliminado(!empleado.isEliminado());
            return empleadoRepository.save(empleado);
        } catch (Exception e) {
            throw new Exception("Error al eliminar el empleado: " + e.getMessage());
        }
    }
}
