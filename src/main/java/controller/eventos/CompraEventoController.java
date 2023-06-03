/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.eventos;

import clases.evento;
import com.proyect.emsa.App;
import clases.eventoSeleccionado;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import model.conexion;
import model.modelo;

/**
 * FXML Controller class
 *
 * @author Eleazar
 */
public class CompraEventoController implements Initializable {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private TableColumn<evento, String> colFecha;

    @FXML
    private TableColumn<evento, String> colHora;

    @FXML
    private ImageView ivImagen;

    @FXML
    private Label lbNombreEvento;

    @FXML
    private Label lblFechaFinal;

    @FXML
    private Label lblFechaInicio;

    @FXML
    private Label lblPrecioA;

    @FXML
    private Label lblPrecioB;

    @FXML
    private Label lblPrecioVip;

    @FXML
    private Label lblPrecioVipM;

    @FXML
    private TableView<evento> tvFechasEvento;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private Label lblTituloEvento;

    private conexion connPosgres;

    private App appObj = new App();

    private Integer CodigoEvento;

    private ObservableList<evento> listaRegistros = FXCollections.observableArrayList();

    public CompraEventoController() {
        this.CodigoEvento = eventoSeleccionado.getCodigoEvento();
        this.connPosgres = new conexion();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelo modeloObj = new modelo();
        System.out.println(this.CodigoEvento);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ResultSet datosEvento = modeloObj.ejecutarConsulta("SELECT * FROM evento WHERE codigoEvento= " + this.CodigoEvento);
            while (datosEvento.next()) {
                lblTituloEvento.setText(datosEvento.getString("nombreevento"));
                lbNombreEvento.setText(datosEvento.getString("nombreevento"));
                txtDescripcion.setText(datosEvento.getString("descripcion"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                String fechaStrVisible = datosEvento.getString("fechainicio");
                LocalDate fechaV = LocalDate.parse(fechaStrVisible);
                lblFechaInicio.setText(fechaV.format(formatter));

                String fechaStrfinalVisible = datosEvento.getString("fechainicio");
                LocalDate fechaVFinal = LocalDate.parse(fechaStrfinalVisible);
                lblFechaFinal.setText(fechaVFinal.format(formatter));

                //lblFechaInicio.setText(datosEvento.getString("fechainicio"));
                //lblFechaFinal.setText(datosEvento.getString("fechafinal"));
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                
                String vippmg = datosEvento.getString("vipmg");
                BigDecimal vipmpgD = new BigDecimal(vippmg);
                String vipmpgDFormateado = decimalFormat.format(vipmpgD);
                lblPrecioVipM.setText("Q " + vipmpgDFormateado);
                //lblPrecioVipM.setText("Q " + datosEvento.getString("vipmg"));

                String vipStr = datosEvento.getString("vip");
                BigDecimal vip = new BigDecimal(vipStr);
                String vipFormateado = decimalFormat.format(vip);
                lblPrecioVip.setText("Q " + vipFormateado);
                
                //lblPrecioVip.setText("Q " + datosEvento.getString("vip"));
                String plan_a = datosEvento.getString("plan_a");
                BigDecimal plan_aD = new BigDecimal(plan_a);
                String plan_aDFormateado = decimalFormat.format(plan_aD);
                lblPrecioA.setText("Q " + plan_aDFormateado);
                //lblPrecioA.setText("Q " + datosEvento.getString("plan_a"));

                String plan_b = datosEvento.getString("plan_b");
                BigDecimal plan_bD = new BigDecimal(plan_b);
                String plan_bDFormateado = decimalFormat.format(plan_bD);
                lblPrecioB.setText("Q " + plan_bDFormateado);
                //lblPrecioB.setText("Q " + datosEvento.getString("plan_b"));
                
                String rutaRelativa = System.getProperty("user.dir") + datosEvento.getString("img");
                File archivoImagen = new File(rutaRelativa);
                if (archivoImagen.exists()) {
                    Image imagen = new Image(archivoImagen.toURI().toString());
                    ivImagen.setImage(imagen);
                } else {
                    rutaRelativa = System.getProperty("user.dir") + "/src/main/resources/img/empty.jpg";
                    archivoImagen = new File(rutaRelativa);
                    Image imagen = new Image(archivoImagen.toURI().toString());
                    ivImagen.setImage(imagen);
                }
                ivImagen.setFitWidth(305); // Ancho deseado
                ivImagen.setFitHeight(208); // Alto deseado
            }

            ResultSet FechaEventos = modeloObj.ejecutarConsulta("SELECT  * FROM fechaevento WHERE codigoevento = " + this.CodigoEvento);
            while (FechaEventos.next()) {
                // ... Código para obtener otros datos del evento ...

                // Crear un objeto RegistroEvento con los datos obtenidos
                evento registros = new evento();
                String fechaStr = FechaEventos.getString("fecha");
                LocalDate fecha = LocalDate.parse(fechaStr);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                registros.setFecha(fecha.format(formatter));
                registros.setHora(FechaEventos.getString("hora"));

                // Agregar el registro a la lista observable
                listaRegistros.add(registros);
            }

            // Asignar la lista observable a la TableView
            tvFechasEvento.setItems(listaRegistros);

            // Asignar los valores de propiedad de columna a las propiedades del objeto RegistroEvento
            colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));

        } catch (SQLException ex) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Algo salió mal." + ex.getMessage());
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();

        }

    }

    @FXML
    void ComprarBoletosEvento(ActionEvent event) {
        System.out.println("Comprar boletos");
    }

    @FXML
    void btnRegresar_clic(ActionEvent event) throws IOException {
        appObj.setRoot("ListaEvento");
    }

}
