package NetDevops.BuenSabor.dto.categoria;

import NetDevops.BuenSabor.dto.BaseDto;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CategoriaEmpresaDTO extends BaseDto {
    private String denominacion;
    private Long empresaId;
    private Boolean eliminado;
    private Set<SubCategoriaConEmpresaDTO> subCategoriaDtos = new HashSet<>();
}
