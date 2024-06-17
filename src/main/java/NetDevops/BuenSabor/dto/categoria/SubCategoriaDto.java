package NetDevops.BuenSabor.dto.categoria;

import NetDevops.BuenSabor.dto.BaseDto;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class SubCategoriaDto extends BaseDto {
    private String denominacion;
    private String urlIcono;
    private Long idCategoriaPadre;
    private Set<SubCategoriaDto> subCategorias = new HashSet<>();
    private boolean eliminado;
}
