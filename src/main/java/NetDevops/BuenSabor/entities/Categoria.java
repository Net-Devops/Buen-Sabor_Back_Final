package NetDevops.BuenSabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"categoriaPadre", "subCategorias"})
public class Categoria extends Base{

   @ManyToMany
   private List<Sucursal> sucursales;

    private String denominacion;
    private String urlIcono;
    @JsonBackReference
    @OneToMany(mappedBy = "categoria")
    private Set<Articulo> articulos = new HashSet<>();
//----------
@OneToMany(mappedBy = "categoriaPadre", cascade = CascadeType.ALL)

private List<Categoria> subCategorias;
//------
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "categoria_padre_id")
    private Categoria categoriaPadre;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public void agregarSubCategoria(Categoria subCategoria) {
        this.subCategorias.add(subCategoria);
    }

}
