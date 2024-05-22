package NetDevops.BuenSabor.dto.Categoria;

import NetDevops.BuenSabor.dto.BaseDto;
import lombok.Data;

@Data
public class SubCategoriaDto extends BaseDto {

    private String denominacion;
    private Long idCategoriaPadre;
    private boolean eliminado;


}
