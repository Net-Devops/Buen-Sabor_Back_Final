package NetDevops.BuenSabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Categoria extends Base{

   @ManyToMany
   private Set<Sucursal> sucursales = new HashSet<>();

    private String denominacion;
    private String urlIcono;
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
