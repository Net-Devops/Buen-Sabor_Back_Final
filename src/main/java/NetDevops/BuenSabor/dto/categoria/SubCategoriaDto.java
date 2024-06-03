package NetDevops.BuenSabor.dto.categoria;

import NetDevops.BuenSabor.dto.BaseDto;
import lombok.Data;

@Data
public class SubCategoriaDto extends BaseDto {

    private String denominacion;
    private String urlIcono;
    private Long idCategoriaPadre;
    private boolean eliminado;


}
