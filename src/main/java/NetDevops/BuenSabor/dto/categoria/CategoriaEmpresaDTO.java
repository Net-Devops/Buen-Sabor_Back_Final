package NetDevops.BuenSabor.dto.categoria;

import NetDevops.BuenSabor.dto.BaseDto;
import lombok.Data;

@Data
public class CategoriaEmpresaDTO extends BaseDto {
    private String denominacion;
    private Long empresaId;
    private Boolean eliminado;
}
