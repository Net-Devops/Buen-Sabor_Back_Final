package NetDevops.BuenSabor.dto.categoria;

import NetDevops.BuenSabor.dto.BaseDto;
import NetDevops.BuenSabor.dto.sucursal.SucursalSimpleDto;
import lombok.Data;


import java.util.HashSet;
import java.util.Set;

@Data
public class CategoriaDto extends BaseDto {

    private String denominacion;
    private String urlIcono;
    private Boolean eliminado;
    private Set<SucursalSimpleDto> sucursales = new HashSet<>();
    private Set<SubCategoriaDto> subCategoriaDtos = new HashSet<>();
}
