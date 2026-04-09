package stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stock.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}