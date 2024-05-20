package NetDevops.BuenSabor.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria extends Base{

//    @ManyToMany(mappedBy = "categorias")
//    //SE AGREGA EL BUILDER.DEFAULT PARA QUE BUILDER NO SOBREESCRIBA LA INICIALIZACION DE LA LISTA
//    @Builder.Default
//    private Set<Sucursal> sucursales = new HashSet<>();

    private String denominacion;
    @OneToMany(mappedBy = "categoria")
    private Set<Articulo> articulos = new HashSet<>();

    @OneToMany(mappedBy = "categoriaPadre")
    private Set<Categoria> subCategorias = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "categoria_padre_id")
    private Categoria categoriaPadre;


    public void agregarSubCategoria(Categoria subCategoria) {
        this.subCategorias.add(subCategoria);
    }
}
