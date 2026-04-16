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
import stock.service.CategoriaService;
import stock.service.ProductoService;
import stock.service.UsuarioService;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class StockApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args); //Arranca la aplicacion
	}
	//Dice a spring que tiene que ejecutar lo siguiente
	@Bean
	CommandLineRunner initData(CategoriaRepository categoriaRepository, CategoriaService categoriaService, ProductoService productoService, UsuarioService usuarioService) {
		return (args) -> {
			usuarioService.añadir(new Usuario("Diego", "ADMIN"));
			usuarioService.añadir(new Usuario("Roberto", "Usuario"));
			usuarioService.obtenerUsuarios().forEach((u) -> {
				PrintStream var10000 = System.out;
				String var10001 = u.getNombre();
				var10000.println(var10001 + " | Rol: " + u.getRol());
			});
		};
	}

}
