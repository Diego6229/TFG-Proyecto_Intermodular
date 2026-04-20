package stock.service;

import org.springframework.stereotype.Service;
import stock.model.MovimientoStock;
import stock.model.Producto;
import stock.repository.MovimientoStockRepository;
import stock.repository.ProductoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoStockService {
    private final MovimientoStockRepository movimientoStockRepository;
    private final ProductoRepository productoRepository;

    public MovimientoStockService(MovimientoStockRepository movimientoStockRepository, ProductoRepository productoRepository) {
        this.movimientoStockRepository = movimientoStockRepository;
        this.productoRepository = productoRepository;
    }

    public List<MovimientoStock> obtenerMovimientos() {
        return this.movimientoStockRepository.findAll();
    }

    public MovimientoStock registrar(MovimientoStock movimientoStock) {

        //Buscar el producto
        Optional<Producto> resultado = productoRepository.findById(movimientoStock.getProducto().getId());
        if(resultado.isEmpty()){
            throw new RuntimeException("Producto no encontrado");
        }

        Producto producto = resultado.get();

        //Logica segun ENTRADA/SALIDA
        if(movimientoStock.getTipo().equals("ENTRADA")){
            producto.setStockActual(producto.getStockActual() + movimientoStock.getCantidad());

        }else if(movimientoStock.getTipo().equals("SALIDA")){
            if(producto.getStockActual() < movimientoStock.getCantidad()) {
                throw new RuntimeException("Stock insuficiente. Disponible: " + producto.getStockActual() + ", solicitado: " + movimientoStock.getCantidad());
            }

            producto.setStockActual(producto.getStockActual() - movimientoStock.getCantidad());
            }

            // Guardar producto con stock actualizado
            productoRepository.save(producto);

            //Establecer fecha y guardar movimiento
            movimientoStock.setFecha(LocalDateTime.now());
            return movimientoStockRepository.save(movimientoStock);
        }
    }





