package NetDevops.BuenSabor.dto.categoria;

import NetDevops.BuenSabor.dto.BaseDto;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CategoriaDto extends BaseDto {

    private String denominacion;
    private String urlIcono;
    private Boolean eliminado;
    private Set<SubCategoriaDto> subCategoriaDtos = new HashSet<>();
    private Set<CategoriaDto> subCategorias = new HashSet<>();
}