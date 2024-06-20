package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.dto.articuloManufacturado.ArticuloManufacturadoTablaDto;
import NetDevops.BuenSabor.dto.categoria.CategoriaDto;
import NetDevops.BuenSabor.dto.categoria.SubCategoriaDto;
import NetDevops.BuenSabor.dto.promocion.ArticuloPromocionDto;
import NetDevops.BuenSabor.dto.promocion.PromocionDetalleDto;
import NetDevops.BuenSabor.dto.promocion.PromocionDto;
import NetDevops.BuenSabor.entities.*;
import NetDevops.BuenSabor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocalService {
    @Autowired
    private ICategoriaRepository categoriaRepository;
    @Autowired
    private ISucursalRepository sucursalRepository;
    @Autowired
    private IAriticuloInsumoRepository articuloInsumoRepository;
    @Autowired
    private IPromocionRepository promocionRepository;
    @Autowired
    private IPromocionDetalleRepository promocionDetalleRepository;
    @Autowired
    private IArticuloManufacturadoRepository articuloManufacturadoRepository;

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
    public List<PromocionDto> buscarPromocionesPorSucursal(Long sucursalId) throws Exception{
    try{
        List<Promocion> promociones = promocionRepository.findBySucursales_Id(sucursalId);
        List<PromocionDto> dtos = new ArrayList<>();
        for (Promocion promocion : promociones) {
            dtos.add(convertToDto(promocion));
        }
        return dtos;
    } catch (Exception e) {
        throw new Exception(e.getMessage() + "No se pudo encontrar la promocion");
    }
}

   public List<PromocionDetalleDto> buscarDetallesPorPromocion(Long promocionId) {
    List<PromocionDetalle> promocionDetalles = promocionDetalleRepository.findByPromocion_Id(promocionId);
    List<PromocionDetalleDto> dtos = new ArrayList<>();
    for (PromocionDetalle promocionDetalle : promocionDetalles) {
        dtos.add(convertToDto(promocionDetalle));
    }
    return dtos;
}

//region Convertir a DTO
public PromocionDto convertToDto(Promocion promocion) {
    PromocionDto dto = new PromocionDto();
    dto.setId(promocion.getId());
    dto.setEliminado(promocion.isEliminado());
    dto.setDenominacion(promocion.getDenominacion());
    dto.setFechaDesde(promocion.getFechaDesde());
    dto.setFechaHasta(promocion.getFechaHasta());
    dto.setHoraDesde(promocion.getHoraDesde());
    dto.setHoraHasta(promocion.getHoraHasta());
    dto.setDescripcionDescuento(promocion.getDescripcionDescuento());
    dto.setPrecioPromocional(promocion.getPrecioPromocional());
    dto.setTipoPromocion(promocion.getTipoPromocion());
    dto.setImagenes(promocion.getImagenes());
    //dto.setSucursales(promocion.getSucursales());
//    for (PromocionDetalle promocionDetalle : promocion.getPromocionDetalles()) {
//        dto.getPromocionDetallesDto().add(convertToDto(promocionDetalle));
//    }
    return dto;
}


public ArticuloPromocionDto convertToDto(ArticuloManufacturado articuloManufacturado) {
    ArticuloPromocionDto dto = new ArticuloPromocionDto();
    dto.setId(articuloManufacturado.getId());
    dto.setEliminado(articuloManufacturado.isEliminado());
    dto.setDenominacion(articuloManufacturado.getDenominacion());
    dto.setDescripcion(articuloManufacturado.getDescripcion());
    dto.setPrecioVenta(articuloManufacturado.getPrecioVenta());
    dto.setTiempoEstimadoMinutos(articuloManufacturado.getTiempoEstimadoMinutos());
    dto.setPreparacion(articuloManufacturado.getPreparacion());
    dto.setImagenes(articuloManufacturado.getImagenes());
    dto.setCodigo(articuloManufacturado.getCodigo());
    dto.setUnidadMedida(articuloManufacturado.getUnidadMedida());
    return dto;
}

public PromocionDetalleDto convertToDto(PromocionDetalle promocionDetalle) {
    PromocionDetalleDto dto = new PromocionDetalleDto();
    dto.setId(promocionDetalle.getId());
    dto.setEliminado(promocionDetalle.isEliminado());
    dto.setCantidad(promocionDetalle.getCantidad());
    dto.setArticuloManufacturadoDto(convertToDto(promocionDetalle.getArticuloManufacturado()));
    dto.setImagenPromocion(promocionDetalle.getImagenPromocion());
    return dto;
}
//endregion

//endregion

//region Articulos Insumos
    public Set<ArticuloInsumo> traerArticulosInsumoPorSucursal(Long sucursalId) throws Exception {
       try {
           return articuloInsumoRepository.findBySucursal_Id(sucursalId);
       }catch (Exception e){
           throw new Exception(e.getMessage());
       }
    }

public ArticuloInsumo aumentarStock(Long id, Integer cantidad, Double nuevoPrecioVenta, Double nuevoPrecioCompra) throws Exception {
    try {
        // Buscar el ArticuloInsumo en la base de datos
        Optional<ArticuloInsumo> optionalArticuloInsumo = articuloInsumoRepository.findById(id);
        if (!optionalArticuloInsumo.isPresent()) {
            throw new Exception("No se encontró el ArticuloInsumo con id " + id);
        }

        // Aumentar el stockActual y actualizar los precios
        ArticuloInsumo articuloInsumo = optionalArticuloInsumo.get();
        articuloInsumo.setStockActual(articuloInsumo.getStockActual() + cantidad);
        articuloInsumo.setPrecioVenta(nuevoPrecioVenta);
        articuloInsumo.setPrecioCompra(nuevoPrecioCompra);

        // Guardar el ArticuloInsumo actualizado en la base de datos
        return articuloInsumoRepository.save(articuloInsumo);
    } catch (Exception e) {
        throw new Exception("Error al aumentar el stock y actualizar los precios: " + e.getMessage());
    }
}





    //endregion

//region Articulo Manufacturado

   public List<ArticuloManufacturadoTablaDto> buscarArticulosPorSucursal(Long sucursalId) throws Exception {
    try {
        // Buscar los ArticuloManufacturado en la base de datos asociados a la Sucursal
        List<ArticuloManufacturado> articulos = articuloManufacturadoRepository.findBySucursal_Id(sucursalId);
        if (articulos.isEmpty()) {
            throw new Exception("No se encontraron ArticulosManufacturados para la Sucursal con id " + sucursalId);
        }
        // Convertir a DTO antes de devolver
        List<ArticuloManufacturadoTablaDto> articulosDto = new ArrayList<>();
        for (ArticuloManufacturado articulo : articulos) {
            articulosDto.add(convertirArticuloManufacturadoToDto(articulo));
        }
        return articulosDto;
    } catch (Exception e) {
        throw new Exception("Error al buscar los ArticulosManufacturados: " + e.getMessage());
    }
}

    //region Convertir a DTO
    public ArticuloManufacturadoTablaDto convertirArticuloManufacturadoToDto(ArticuloManufacturado articulo) {
    ArticuloManufacturadoTablaDto dto = new ArticuloManufacturadoTablaDto();
    dto.setId(articulo.getId());
    dto.setCodigo(articulo.getCodigo());
    dto.setDenominacion(articulo.getDenominacion());
    // Asegúrate de tener un método para obtener la URL de la imagen principal
        if (!articulo.getImagenes().isEmpty()) {
            ImagenArticulo primeraImagen = articulo.getImagenes().iterator().next();
            dto.setImagen(primeraImagen.getUrl());
            dto.setDenominacion(primeraImagen.getUrl());
        }
    dto.setPrecioVenta(articulo.getPrecioVenta());
    dto.setDescripcion(articulo.getDescripcion());
    dto.setTiempoEstimadoCocina(articulo.getTiempoEstimadoMinutos());
    return dto;
}

    //endregion


//endregion


}



