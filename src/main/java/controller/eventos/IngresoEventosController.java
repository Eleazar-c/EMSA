/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.eventos;

import clases.evento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    private TableView<evento> tvFechasEvento;

     @FXML
    private TableColumn colFecha;

    @FXML
    private TableColumn colHora;

    @FXML
    private TableColumn<evento, String> colFecha2;

    @FXML
    private TableColumn<evento, String> colHora2;
    
    @FXML
    private TextField txtHora;

    @FXML
    private DatePicker dpFecha;

    private ObservableList<evento> evento;
    
     @FXML
    private ImageView ivImagen;

    @FXML
    private DatePicker dpFechaFiinal;

    @FXML
    private DatePicker dpFechaInicio;

    @FXML
    private TextArea txtDescripcion;


    @FXML
    private TextField  txtNombreEvento;

    @FXML
    private TextField txtPlanA;

    @FXML
    private TextField txtPlanB;

    @FXML
    private TextField txtVip;

    @FXML
    private TextField txtVipMg;

    private String rutaImagen;



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
            evento p = new evento();
            p.setFecha(fecha);
            p.setHora(hora);

            // Compruebo si la Fecha esta en el lista
            if (!this.evento.contains(p)) {
                // Lo añado a la lista
                this.evento.add(p);
                // Seteo los items
                this.tvFechasEvento.setItems(evento);
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
            //guardamos la ruta de la imagen en una variable
            rutaImagen = imgFile.getAbsolutePath();

            Image image = new Image("file:" + imgFile.getAbsolutePath());
            ivImagen.setImage(image);
        }
    }
    
     @FXML
    void registrarEvento(ActionEvent event) {
         evento eventoObj = new evento();


         /*
         eventoObj.setNombreEvento(txtNombreEvento.getText());
         eventoObj.setSinopsis(txtDescripcion.getText());
         eventoObj.setFechaInicioVisible(dpFechaInicio.getValue().toString());
         eventoObj.setFechaFinalVisible((dpFechaFiinal.getValue().toString()));
         eventoObj.setPrecioVIPMG(Float.parseFloat(txtVipMg.getText()));
         eventoObj.setVIP(Float.parseFloat(txtVip.getText()));
         eventoObj.setPantlaA(Float.parseFloat(txtPlanA.getText()));
         eventoObj.setPlantaB(Float.parseFloat(txtPlanA.getText()));

         */

         //Metemos las fechas en un array
         ArrayList<evento> eventosArray = new ArrayList<>();
         ObservableList<evento> items = tvFechasEvento.getItems(); // Obtiene la lista de elementos de la TableView

         for (evento evento : items) {
             eventosArray.add(evento); // Agrega cada evento a la lista
         }

         // Obtener la imagen del ImageView
       /*
         for (evento evento : items) {
             String fecha = (String) colFecha.getCellData(evento); // Obtiene el valor de la columna "Fecha" para cada Evento
             String hora = (String) colHora.getCellData(evento); // Obtiene el valor de la columna "Hora" para cada Evento
             System.out.println("Fecha: " + fecha + ", Hora: " + hora); // Imprime los valores de la columna "Fecha" y "Hora"
         }
*/

         
    }
}
