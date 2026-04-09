package stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stock.model.MovimientoStock;

public interface MovimientoStockRepository extends JpaRepository<MovimientoStock, Long> {
}