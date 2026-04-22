package stock;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication

//Arranca SP y JavaFX
public class StockApplication extends Application {

	//Guardar contexto de Spring
	private ConfigurableApplicationContext SpringBootContext;

	//metodo que se ejecuta antes de abrir la ventana
	@Override
	public void init() {
		SpringBootContext = SpringApplication.run(StockApplication.class);
	}

	//metodo que se ejecuta cuando JavaFX esta listo
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));

		//JavaFX usa SP para crear los controllers
		loader.setControllerFactory(ContextoSpring.getContexto()::getBean);

		Scene scene = new Scene(loader.load());
		stage.setScene(scene);
		stage.setTitle("Stock Application");
		stage.show();
	}

	//metodo que se ejecuta al cerrar la app
	@Override
	public void stop() throws Exception {
		SpringBootContext.close();
	}


}

