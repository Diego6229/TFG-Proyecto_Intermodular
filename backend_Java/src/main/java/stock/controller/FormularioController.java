package stock.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stock.model.Categoria;
import stock.model.Producto;
import stock.service.CategoriaService;
import stock.service.ProductoService;

@Component
public class FormularioController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<Categoria> cmbCategoria;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtStockActual;

    @FXML
    private TextField txtStockMinimo;

    @FXML
    public void initialize() {
        //Cargar las categorias de la BD en el comboBox
        cmbCategoria.getItems().addAll(categoriaService.obtenerCategorias());
    }

    @FXML
    void cancelar(ActionEvent event) {
        btnCancelar.getScene().getWindow().hide();
    }

    @FXML
    void guardar(ActionEvent event) {
        //Recoger los datos escritos
        String nombre = txtNombre.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        int stockActual = Integer.parseInt(txtStockActual.getText());
        int stockMinimo = Integer.parseInt(txtStockMinimo.getText());
        Categoria categoria = cmbCategoria.getValue();

        //Crear nuevo producto
        Producto productoNuevo = new Producto();
        productoNuevo.setNombre(nombre);
        productoNuevo.setPrecio(precio);
        productoNuevo.setStockActual(stockActual);
        productoNuevo.setStockMinimo(stockMinimo);
        productoNuevo.setCategoria(categoria);

        //Guardar en la BD
        productoService.añadir(productoNuevo);

        //Cerrar la ventana
        btnGuardar.getScene().getWindow().hide();
    }

}
