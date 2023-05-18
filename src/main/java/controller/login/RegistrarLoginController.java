/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Eleazar
 */
public class RegistrarLoginController implements Initializable {

     @FXML
    private Button btnRegistrarU;

    @FXML
    private Hyperlink hpLogin;

    @FXML
    private PasswordField txtClave;

    @FXML
    private TextField txtNombreCompleto;

    @FXML
    private TextField txtNombreUsuario;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    void btnRegistrarU_clic(ActionEvent event) {

    }

    @FXML
    void hlLogin_clic(ActionEvent event) {

    }
    
}
