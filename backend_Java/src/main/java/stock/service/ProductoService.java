
package stock.service;

import java.util.List;
import org.springframework.stereotype.Service;
import stock.model.Categoria;
import stock.model.Producto;
import stock.repository.ProductoRepository;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> obtenerProductos() {
        return this.productoRepository.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return (Producto)this.productoRepository.findById(id).get();
    }

    public Producto añadir(Producto producto) {
        return (Producto)this.productoRepository.save(producto);
    }

    public void eliminar(Long id) {
        this.productoRepository.deleteById(id);
    }

    public List<Producto> obtenerProductosPorCategoria(Categoria categoria) {
        return this.productoRepository.findByCategoria(categoria);
    }

    public List<Producto> obtenerConStockBajo() {
        return this.productoRepository.findAll().stream().filter((product) -> product.getStockActual() < product.getStockMinimo()).toList();
    }
}