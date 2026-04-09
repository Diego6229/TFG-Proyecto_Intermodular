package stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stock.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
