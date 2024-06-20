package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.dto.categoria.CategoriaDto;
import NetDevops.BuenSabor.dto.categoria.SubCategoriaDto;
import NetDevops.BuenSabor.entities.ArticuloInsumo;
import NetDevops.BuenSabor.entities.Categoria;
import NetDevops.BuenSabor.entities.Sucursal;
import NetDevops.BuenSabor.repository.IAriticuloInsumoRepository;
import NetDevops.BuenSabor.repository.ICategoriaRepository;
import NetDevops.BuenSabor.repository.ISucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LocalService {
    @Autowired
    private ICategoriaRepository categoriaRepository;
    @Autowired
    private ISucursalRepository sucursalRepository;
    @Autowired
    private IAriticuloInsumoRepository articuloInsumoRepository;

//region  Categoria
    public Set<CategoriaDto> traerTodo(Long sucursalId) throws Exception {
    try {
        Set<Categoria> listaCategoriaOriginal = categoriaRepository.findBySucursales_Id(sucursalId);
        Set<CategoriaDto> listaDto = new HashSet<>();
        for (Categoria lista: listaCategoriaOriginal){
            CategoriaDto categoriadto = new CategoriaDto();
            categoriadto.setDenominacion(lista.getDenominacion());
            categoriadto.setUrlIcono(lista.getUrlIcono());
            categoriadto.setId(lista.getId());
            categoriadto.setEliminado(lista.isEliminado());

            Set<Categoria> subCategorias = categoriaRepository.findByCategoriaPadre_Id(lista.getId());

            for (Categoria subCategoria : subCategorias) {
                SubCategoriaDto subCategoriaDto = agregarSubCategoriasRecursivamente(subCategoria, sucursalId);
                categoriadto.getSubCategoriaDtos().add(subCategoriaDto);
            }
            listaDto.add(categoriadto);
        }
        return listaDto;
    } catch (Exception e) {
        throw new Exception(e);
    }
}

private SubCategoriaDto agregarSubCategoriasRecursivamente(Categoria categoria, Long sucursalId) {
    SubCategoriaDto subCategoriaDto = new SubCategoriaDto();
    subCategoriaDto.setDenominacion(categoria.getDenominacion());
    subCategoriaDto.setUrlIcono(categoria.getUrlIcono());
    subCategoriaDto.setId(categoria.getId());
    subCategoriaDto.setIdCategoriaPadre(categoria.getCategoriaPadre() != null ? categoria.getCategoriaPadre().getId() : null);
    subCategoriaDto.setEliminado(categoria.isEliminado());

    Set<Categoria> subCategorias = categoriaRepository.findByCategoriaPadre_IdAndSucursales_Id(categoria.getId(), sucursalId);
    for (Categoria subCategoria : subCategorias) {
        SubCategoriaDto subSubCategoriaDto = agregarSubCategoriasRecursivamente(subCategoria, sucursalId);
        subCategoriaDto.getSubSubCategoriaDtos().add(subSubCategoriaDto);
    }
    return subCategoriaDto;
}

    public Categoria agregarSucursalACategoria(Long categoriaId, Long sucursalId) throws Exception {
        Categoria categoria = categoriaRepository.findById(categoriaId).orElse(null);
        Sucursal sucursal = sucursalRepository.findById(sucursalId).orElse(null);

        if (categoria == null || sucursal == null) {
            throw new Exception("La categoría o la sucursal no existen");
        }

        categoria.getSucursales().add(sucursal);
        return categoriaRepository.save(categoria);
    }

    public Categoria desasociarSucursalDeCategoria(Long categoriaId, Long sucursalId) throws Exception {
    Categoria categoria = categoriaRepository.findById(categoriaId).orElse(null);
    Sucursal sucursal = sucursalRepository.findById(sucursalId).orElse(null);

    if (categoria == null || sucursal == null) {
        throw new Exception("La categoría o la sucursal no existen");
    }

    categoria.getSucursales().remove(sucursal);
    return categoriaRepository.save(categoria);
}

public Set<CategoriaDto> traerCategoriasNoAsociadasASucursal(Long sucursalId) throws Exception {
    try {
        Sucursal sucursal = sucursalRepository.findById(sucursalId).orElse(null);
        if (sucursal == null) {
            throw new Exception("La sucursal no existe");
        }

        Set<Categoria> listaCategoriaOriginal = categoriaRepository.findBySucursalesNotContains(sucursal);
        Set<CategoriaDto> listaDto = new HashSet<>();
        for (Categoria lista: listaCategoriaOriginal){
            CategoriaDto categoriadto = new CategoriaDto();
            categoriadto.setDenominacion(lista.getDenominacion());
            categoriadto.setUrlIcono(lista.getUrlIcono());
            categoriadto.setId(lista.getId());
            categoriadto.setEliminado(lista.isEliminado());

            Set<Categoria> subCategorias = categoriaRepository.findByCategoriaPadre_IdAndSucursalesNotContains(lista.getId(), sucursal);

            for (Categoria subCategoria : subCategorias) {
                SubCategoriaDto subCategoriaDto = agregarSubCategoriasNoAsociadasASucursalRecursivamente(subCategoria, sucursalId);
                categoriadto.getSubCategoriaDtos().add(subCategoriaDto);
            }
            listaDto.add(categoriadto);
        }
        return listaDto;
    } catch (Exception e) {
        throw new Exception(e);
    }
}

private SubCategoriaDto agregarSubCategoriasNoAsociadasASucursalRecursivamente(Categoria categoria, Long sucursalId) throws Exception {
    Sucursal sucursal = sucursalRepository.findById(sucursalId).orElse(null);
    if (sucursal == null) {
        throw new Exception("La sucursal no existe");
    }

    SubCategoriaDto subCategoriaDto = new SubCategoriaDto();
    subCategoriaDto.setDenominacion(categoria.getDenominacion());
    subCategoriaDto.setUrlIcono(categoria.getUrlIcono());
    subCategoriaDto.setId(categoria.getId());
    subCategoriaDto.setIdCategoriaPadre(categoria.getCategoriaPadre() != null ? categoria.getCategoriaPadre().getId() : null);
    subCategoriaDto.setEliminado(categoria.isEliminado());

    Set<Categoria> subCategorias = categoriaRepository.findByCategoriaPadre_IdAndSucursalesNotContains(categoria.getId(), sucursal);
    for (Categoria subCategoria : subCategorias) {
        SubCategoriaDto subSubCategoriaDto = agregarSubCategoriasNoAsociadasASucursalRecursivamente(subCategoria, sucursalId);
        subCategoriaDto.getSubSubCategoriaDtos().add(subSubCategoriaDto);
    }
    return subCategoriaDto;
}

//endregion

//region Promociones

//endregion

//region Articulos Insumos
    public Set<ArticuloInsumo> traerArticulosInsumoPorSucursal(Long sucursalId) throws Exception {
       try {
           return articuloInsumoRepository.findBySucursal_Id(sucursalId);
       }catch (Exception e){
           throw new Exception(e.getMessage());
       }
    }


    //endregion



}



