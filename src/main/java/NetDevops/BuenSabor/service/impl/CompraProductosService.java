package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.dto.compraProducto.CompraProductoDto;
import NetDevops.BuenSabor.entities.Articulo;
import NetDevops.BuenSabor.entities.ArticuloInsumo;
import NetDevops.BuenSabor.entities.ArticuloManufacturado;
import NetDevops.BuenSabor.repository.IAriticuloInsumoRepository;
import NetDevops.BuenSabor.repository.IArticuloManufacturadoRepository;
import NetDevops.BuenSabor.repository.IArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CompraProductosService {

    @Autowired
    private IArticuloManufacturadoRepository articuloManufacturadoRepository;

    @Autowired
    private IAriticuloInsumoRepository articuloInsumoRepository;
    @Autowired
    private IArticuloRepository articuloRepository;

    public List<CompraProductoDto> findArticulosByCategoria(Long categoriaId) {
        List<ArticuloManufacturado> articulosManufacturados = articuloManufacturadoRepository.findByCategoriaId(categoriaId);
        List<ArticuloInsumo> articulosInsumos = articuloInsumoRepository.findByCategoriaId(categoriaId);

        List<CompraProductoDto> articulos = new ArrayList<>();
        for (ArticuloManufacturado articulo : articulosManufacturados) {
            CompraProductoDto dto = new CompraProductoDto();
            dto.setId(articulo.getId());
            dto.setDenominacion(articulo.getDenominacion());
            dto.setDescripcion(articulo.getDescripcion());
            dto.setCodigo(articulo.getCodigo());
            dto.setPrecioVenta(articulo.getPrecioVenta());
            dto.setImagenes(new ArrayList<>(articulo.getImagenes())); // Convert Set to List
            dto.setCategoriaId(articulo.getCategoria().getId());
            if (articulo.getSucursal() != null) {
                dto.setSucursalId(articulo.getSucursal().getId());
            }
            articulos.add(dto);
        }

        for (ArticuloInsumo articulo : articulosInsumos) {
            CompraProductoDto dto = new CompraProductoDto();
            dto.setId(articulo.getId());
            dto.setDenominacion(articulo.getDenominacion());
            dto.setDescripcion(articulo.getDescripcion());
            dto.setCodigo(articulo.getCodigo());
            dto.setPrecioVenta(articulo.getPrecioVenta());
            dto.setImagenes(new ArrayList<>(articulo.getImagenes())); // Convert Set to List
            dto.setCategoriaId(articulo.getCategoria().getId());
            if (articulo.getSucursal() != null) {
                dto.setSucursalId(articulo.getSucursal().getId());
            }
            articulos.add(dto);
        }

        return articulos;
    }

    public CompraProductoDto buscarArticuloPorId(Long id) {
        Articulo articulo = articuloRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Articulo no encontrado con id: " + id));

        CompraProductoDto dto = new CompraProductoDto();
        dto.setId(articulo.getId());
        dto.setDenominacion(articulo.getDenominacion());
        dto.setDescripcion(articulo.getDescripcion());
        dto.setCodigo(articulo.getCodigo());
        dto.setPrecioVenta(articulo.getPrecioVenta());
        dto.setImagenes(new ArrayList<>(articulo.getImagenes()));
        dto.setCategoriaId(articulo.getCategoria().getId());
        if (articulo.getSucursal() != null) {
            dto.setSucursalId(articulo.getSucursal().getId());
        }
        return dto;

    }

}
