/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.proyect.emsa.App;

/**
 * FXML Controller class
 *
 * @author Eleazar
 */
public class InicioController implements Initializable {

    @FXML
    private Button btnEventos;

    @FXML
    private Button btnIngresarEv;

    @FXML
    private Button btnUsuarioIngresar;

    @FXML
    private Label lblNombre;

    private App appobj = new App();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void btnEventosClic(ActionEvent event) {

    }

    @FXML
    void btnIngresarUsuario_clic(ActionEvent event) throws IOException {
        appobj.setRoot("registroUsuario");
    }

    @FXML
    void btnIngresarEvento_click(ActionEvent event) throws IOException {
        appobj.setRoot("IngresoEventos");
    }
    
}
