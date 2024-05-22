package NetDevops.BuenSabor.dto.Categoria;

import NetDevops.BuenSabor.dto.BaseDto;
import lombok.Data;


import java.util.HashSet;
import java.util.Set;

@Data
public class CategoriaDto extends BaseDto {

    private String denominacio;
    private Set<SubCategoriaDto> subCategoriaDtos = new HashSet<>();
}
