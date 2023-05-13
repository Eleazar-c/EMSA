/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.eventos;

import clases.evento;
import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Eleazar
 */
public class IngresoEventosController implements Initializable {

    @FXML
    private Button agregar;
    
    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private TableView<evento> tblTiempoEveneto;

     @FXML
    private TableColumn colFecha;

    @FXML
    private TableColumn colHora;
    
    @FXML
    private TextField txtHora;

    @FXML
    private DatePicker dpFecha;

    private ObservableList<evento> evento;
    
     @FXML
    private ImageView ivImagen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Creo el observablelist
        evento = FXCollections.observableArrayList();

        // Asigno las columnas con los atributos del modelo
        this.colFecha.setCellValueFactory(new PropertyValueFactory("Fecha"));
        this.colHora.setCellValueFactory(new PropertyValueFactory("Hora"));
    }

    @FXML
      void btnAgregarFecha(ActionEvent event) {
        try {

            // Obtengo los datos del formulario
            String fecha = this.dpFecha.getValue().toString();
            String hora = this.txtHora.getText();
            
            // Creo una Fecha
            evento p = new evento(fecha, hora);

            // Compruebo si la Fecha esta en el lista
            if (!this.evento.contains(p)) {
                // Lo añado a la lista
                this.evento.add(p);
                // Seteo los items
                this.tblTiempoEveneto.setItems(evento);
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("La fecha existe");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }

    }

    @FXML
    void subirImaggen(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(null);

        // Mostar la imagen
        if (imgFile != null) {
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            ivImagen.setImage(image);
        }
    }
    
     @FXML
    void registrarEvento(ActionEvent event) {

    }
}
