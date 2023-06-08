/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import clases.SessionSistema;
import clases.login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.proyect.emsa.App;
import  clases.SessionSistema;


/**
 * FXML Controller class
 *
 * @author Eleazar
 */
public class InicioController implements Initializable {

    @FXML
    private Button btnEventos;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnIngresarEv;

    @FXML
    private Button btnUsuarioIngresar;

    @FXML
    private Label lblNombre;

    private App appobj = new App();
    private SessionSistema session = new SessionSistema();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblNombre.setText( session.getNombre() );
        lblNombre.setVisible(true);
        if(SessionSistema.getCodigoRol() == 1){
            btnEventos.setVisible(false);
        }else if(SessionSistema.getCodigoRol() == 2){
            btnIngresarEv.setVisible(false);
            btnUsuarioIngresar.setVisible(false);
        }


    }

    @FXML
    void btnEventosClic(ActionEvent event) throws IOException {
        appobj.setRoot("ListaEvento");

    }

    @FXML
    void btnIngresarUsuario_clic(ActionEvent event) throws IOException {
        appobj.setRoot("registroUsuario");
    }

    @FXML
    void btnIngresarEvento_click(ActionEvent event) throws IOException {
        appobj.setRoot("IngresoEventos");
    }

    @FXML
    void btnCerrarSesion_clic(ActionEvent event) throws IOException {
        session.setNombre("");
        session.setCorreo("");
        session.setCodigoRol(0);
        session.setCodigoUsuario(0);
        session.setCodigoEstado(0);

        appobj.setRoot("login");
    }
}
