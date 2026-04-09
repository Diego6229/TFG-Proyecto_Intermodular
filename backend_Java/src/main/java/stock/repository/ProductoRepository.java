package stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stock.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}