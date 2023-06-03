/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.eventos;

import clases.evento;

import com.proyect.emsa.App;
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
import javafx.stage.StageStyle;
import model.eventoModelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
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
    private Button btnCancelar;

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
    private TextField txtNombreEvento;

    @FXML
    private TextField txtPlanA;

    @FXML
    private TextField txtPlanB;

    @FXML
    private TextField txtVip;

    @FXML
    private TextField txtVipMg;

    private String rutaImagen;
    private String nombreImagen;
    private String rutaFinalArchivo;
    private eventoModelo eventoModeloObj = new eventoModelo();

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
        /*
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
        String nombreImagen = imgFile.getName();
        this.nombreImagen = nombreImagen;
        // Mostar la imagen
        if (imgFile != null) {

            //guardamos la ruta de la imagen en una variable
            rutaImagen = imgFile.getAbsolutePath();

            try {
                FileInputStream inputStream = new FileInputStream(imgFile);
                inputStream.close();
                
                Image image = new Image("file:" + imgFile.getAbsolutePath());
                ivImagen.setImage(image);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
         */
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la búsqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(null);
        String nombreImagen = imgFile.getName();
        this.nombreImagen = nombreImagen;

        if (imgFile != null) {
            try (FileInputStream inputStream = new FileInputStream(imgFile)){
                File tempDir = new File(System.getProperty("java.io.tmpdir"));
                File tempFile = File.createTempFile("temp", imgFile.getName(), tempDir);
                rutaImagen = tempFile.getAbsolutePath();

                // Copiar la imagen seleccionada al archivo temporal
                Files.copy(imgFile.toPath(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                Image image = new Image("file:" + imgFile.getAbsolutePath());
                ivImagen.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Guardar la imagen en una ruta temporal

    }

    private String saveImage(String path, String nombre) {
        String pathResource = System.getProperty("user.dir");
        String RutaRelativa = pathResource + "/src/main/resources/img/" + nombre;
        this.rutaFinalArchivo = "/src/main/resources/img/" + nombre;
        Path source = Paths.get(path);
        Path target = Paths.get(pathResource, "/src/main/resources/img/" + nombre);
        ivImagen.setImage(null);
        System.out.println("Source: "+source);

        System.out.println("Tarjet: "+target);
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            return RutaRelativa;
        } catch (IOException ex) {
            System.out.println("Error al Guardar la Imagen: " + ex.getMessage());
            return "";
        }
    }

    @FXML
    void registrarEvento(ActionEvent event) {
        evento eventoObj = new evento();

        eventoObj.setNombreEvento(txtNombreEvento.getText());
        eventoObj.setSinopsis(txtDescripcion.getText());
        eventoObj.setFechaInicioVisible( Date.valueOf(dpFechaInicio.getValue()));
        eventoObj.setFechaFinalVisible(Date.valueOf(dpFechaFiinal.getValue()));
        eventoObj.setPrecioVIPMG(Float.parseFloat(txtVipMg.getText()));
        eventoObj.setVIP(Float.parseFloat(txtVip.getText()));
        eventoObj.setPantlaA(Float.parseFloat(txtPlanA.getText()));
        eventoObj.setPlantaB(Float.parseFloat(txtPlanA.getText()));
        /*
        eventoObj.setLinkImg(saveImage(rutaImagen, nombreImagen));
        if (eventoObj.getLinkImg() == "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Algo salio mal.");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
            return;
        }
         */
        if (rutaImagen != null && nombreImagen != null) {
            String rutaFinal = saveImage(rutaImagen, nombreImagen);
            if (rutaFinal.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Algo salió mal al guardar la imagen.");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
                return;
            }
            eventoObj.setLinkImg(this.rutaFinalArchivo);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Debes seleccionar una imagen.");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
            return;
        }
        //Metemos las fechas en un array
        ArrayList<evento> eventosArray = new ArrayList<>();
        ObservableList<evento> items = tvFechasEvento.getItems(); // Obtiene la lista de elementos de la TableView
        for (evento evento : items) {
            eventosArray.add(evento); // Agrega cada evento a la lista
        }

        boolean respuesta = eventoModeloObj.registrarEvento(eventoObj, eventosArray);

        if (respuesta) {
            this.saveImage(rutaImagen, this.nombreImagen);
            //Para que muestre una alerta de informacion
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exito");
            alert.setHeaderText(null);
            alert.setContentText("Se registro correctamente.");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();

            limpiarCampos();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Algo salio mal.");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        }

    }

    public void limpiarCampos() {
        txtNombreEvento.setText("");
        txtDescripcion.setText("");
        txtHora.setText("");
        txtVipMg.setText("");
        txtPlanA.setText("");
        txtPlanB.setText("");
        txtVip.setText("");
        dpFechaFiinal.setValue(null);
        dpFechaInicio.setValue(null);
        dpFecha.setValue(null);
        tvFechasEvento.getItems().clear();
    }

    @FXML
    void btnCancelar_clic(ActionEvent event) throws IOException {
        App appObj = new App();
        appObj.setRoot("Inicio");
    }
}
