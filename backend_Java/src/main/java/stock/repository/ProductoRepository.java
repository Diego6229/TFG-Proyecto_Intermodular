package stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stock.model.Categoria;
import stock.model.Producto;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria(Categoria categoria);
}