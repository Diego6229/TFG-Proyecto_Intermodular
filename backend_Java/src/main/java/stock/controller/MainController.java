package stock.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stock.ContextoSpring;
import stock.model.Producto;
import stock.service.ProductoService;

import java.io.IOException;
import java.util.List;

@Component //Hace que SP gestione esta clase
public class MainController {

    @Autowired //SP inyecta el servicio automatico
    private ProductoService productoService;

    @FXML
    private Button botonAniadir;

    @FXML
    private Button botonEstadisticas;

    @FXML
    private TableColumn<Producto, Void> colAcciones;

    @FXML
    private TableColumn<Producto, String> colCategoria;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TableColumn<Producto, Integer> colStockActual;

    @FXML
    private TableColumn<Producto, Integer> colStockMinimo;


    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    public void initialize() { //Cargar productos
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStockActual.setCellValueFactory(new PropertyValueFactory<>("stockActual"));
        colStockMinimo.setCellValueFactory(new PropertyValueFactory<>("stockMinimo"));

        colCategoria.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCategoria().getNombre()));

        List<Producto> productos = productoService.obtenerProductos();
        tablaProductos.getItems().setAll(productos);
    }


    @FXML
    void AniadirProducto(ActionEvent event) {
        try{
            //Cargar el archivo fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/formulario.fxml"));

            //Usa SP para crear el FormularioController
            loader.setControllerFactory(ContextoSpring.getContexto()::getBean);

            //Crear la nueva ventana
            Stage stage = new Stage();
            stage.setTitle("Añadir Producto");
            stage.setScene(new Scene(loader.load()));

            //Bloquear la ventana principal
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            //Recargar la tabla al cerrar el formulario
            tablaProductos.getItems().setAll(productoService.obtenerProductos());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irEstadisticas(ActionEvent event) {

    }

}
