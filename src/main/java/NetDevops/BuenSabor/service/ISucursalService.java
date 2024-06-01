package NetDevops.BuenSabor.service;

import NetDevops.BuenSabor.dto.sucursal.SucursalDto;
import NetDevops.BuenSabor.entities.Sucursal;

import java.util.List;

public interface ISucursalService {
    public Sucursal save(Sucursal sucursal) throws Exception;
    public boolean delete(Long id) throws Exception;
    public Sucursal update(Long id, Sucursal sucursal) throws Exception;
    public List<Sucursal> traerTodo() throws Exception;
    public Sucursal traerPorId(Long id) throws Exception;
    public Sucursal reactivate(Long id) throws Exception;
    public List<Sucursal> traerPorEmpresaId(Long empresaId) throws Exception;
    public Sucursal guardarSucursalDto(SucursalDto sucursalDto) throws Exception;
}
