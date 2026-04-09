package stock;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import stock.model.Categoria;
import stock.model.MovimientoStock;
import stock.model.Producto;
import stock.model.Usuario;
import stock.repository.CategoriaRepository;
import stock.repository.MovimientoStockRepository;
import stock.repository.ProductoRepository;
import stock.repository.UsuarioRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class StockApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args); //Arranca la aplicacion
	}
	//Dice a spring que tiene que ejecutar lo siguiente
	@Bean

	//Ejecuta este codigo cuando la aplicacion arranca
	CommandLineRunner initData(CategoriaRepository categoriaRepository,
							   ProductoRepository productoRepository,
							   UsuarioRepository usuarioRepository,
							   MovimientoStockRepository movimientoStockRepository) {
		return args -> {

			//Crear categoria
			Categoria categoria = new Categoria();
			categoria.setNombre("Bebidas");
			categoriaRepository.save(categoria);

			//Crear producto
			Producto p1 = new Producto();
			p1.setNombre("Coca-Cola");
			p1.setPrecio(1.5);
			p1.setStockActual(50);
			p1.setStockMinimo(5);
			p1.setCategoria(categoria);
			productoRepository.save(p1);

			//Crear usuario
			Usuario u1 = new Usuario();
			u1.setNombre("Jose");
			u1.setRol("ADMIN");
			usuarioRepository.save(u1);

			//Crear movimiento
			MovimientoStock ms1 = new MovimientoStock();
			ms1.setTipo("ENTRADA");
			ms1.setCantidad(50);
			ms1.setFecha(LocalDateTime.now());
			ms1.setProducto(p1);
			ms1.setUsuario(u1);
			movimientoStockRepository.save(ms1);
		};
	}

}
