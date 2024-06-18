package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.dto.compraProducto.CompraPedidoDto;
import NetDevops.BuenSabor.dto.compraProducto.CompraProductoDto;
import NetDevops.BuenSabor.dto.compraProducto.PedidoDetalleDto;
import NetDevops.BuenSabor.entities.*;
import NetDevops.BuenSabor.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class CompraProductosService {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private IArticuloManufacturadoRepository articuloManufacturadoRepository;

    @Autowired
    private IAriticuloInsumoRepository articuloInsumoRepository;
    @Autowired
    private IArticuloRepository articuloRepository;
    @Autowired
    private IPedidoRepository pedidoRepository;
    @Autowired
    private ICategoriaRepository categoriaRepository;

  public List<CompraProductoDto> findArticulosByCategoria(Long categoriaId) {
    Set<Categoria> subcategorias = categoriaRepository.findByCategoriaPadre_Id(categoriaId);

    List<CompraProductoDto> articulos = new ArrayList<>();
    for (Categoria subcategoria : subcategorias) {
        List<ArticuloManufacturado> articulosManufacturados = articuloManufacturadoRepository.findByCategoriaId(subcategoria.getId());
        List<ArticuloInsumo> articulosInsumos = articuloInsumoRepository.findByCategoriaId(subcategoria.getId());

        for (ArticuloManufacturado articulo : articulosManufacturados) {
            boolean canBeCreated = true;
            for (ArticuloManufacturadoDetalle detalle : articulo.getArticuloManufacturadoDetalles()) {
                ArticuloInsumo insumo = detalle.getArticuloInsumo();
                if (insumo.getStockActual() < detalle.getCantidad()) {
                    canBeCreated = false;
                    break;
                }
            }
            if (canBeCreated) {
                CompraProductoDto dto = convertToDto(articulo);
                articulos.add(dto);
            }
        }

        for (ArticuloInsumo articulo : articulosInsumos) {
            CompraProductoDto dto = convertToDto(articulo);
            articulos.add(dto);
        }
    }

    return articulos;
}

private CompraProductoDto convertToDto(Articulo articulo) {
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
    return dto;
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




    public CompraPedidoDto crearPedido(CompraPedidoDto compraPedidoDto) throws Exception {
    try {
        Pedido pedido = new Pedido();
        // Set other properties...
        pedido.setFechaPedido(LocalDate.now());
        pedido.setHora(LocalTime.now());
        pedido.setTotal(compraPedidoDto.getTotal());

        List<PedidoDetalle> pedidoDetalles = new ArrayList<>();
        for (PedidoDetalleDto detalleDto : compraPedidoDto.getPedidoDetalle()) {
            Articulo articulo = articuloRepository.findById(detalleDto.getProducto().getId())
                    .orElseThrow(() -> new NoSuchElementException("Articulo no encontrado con id: " + detalleDto.getProducto().getId()));

            if (articulo instanceof ArticuloInsumo) {
                ArticuloInsumo articuloInsumo = (ArticuloInsumo) articulo;
                // Subtract the quantity from the stock of the ArticuloInsumo
                articuloInsumo.setStockActual(articuloInsumo.getStockActual() - detalleDto.getCantidad());
                // Save the updated ArticuloInsumo in the database
                articuloRepository.save(articuloInsumo);
            } else if (articulo instanceof ArticuloManufacturado) {
                ArticuloManufacturado articuloManufacturado = (ArticuloManufacturado) articulo;
                for (ArticuloManufacturadoDetalle detalle : articuloManufacturado.getArticuloManufacturadoDetalles()) {
                    ArticuloInsumo articuloInsumo = detalle.getArticuloInsumo();
                    // Subtract the quantity needed for the manufacturing from the stock of the ArticuloInsumo
                    articuloInsumo.setStockActual(articuloInsumo.getStockActual() - detalle.getCantidad());
                    // Save the updated ArticuloInsumo in the database
                    articuloRepository.save(articuloInsumo);
                }
            }

            PedidoDetalle detalle = new PedidoDetalle();
            detalle.setArticulo(articulo);
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setPedido(pedido);
            pedidoDetalles.add(detalle);
        }
        pedido.setPedidoDetalle(pedidoDetalles);
        pedidoRepository.save(pedido);
        CompraPedidoDto pedidoDto = modelMapper.map(pedido, CompraPedidoDto.class);

        return pedidoDto;
    } catch (Exception e) {
        throw new Exception(e.getMessage());
    }
}


}
